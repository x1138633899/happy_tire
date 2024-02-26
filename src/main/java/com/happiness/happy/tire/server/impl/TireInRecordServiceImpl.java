package com.happiness.happy.tire.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happiness.happy.tire.entity.QueryParams;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.TireInRecord;
import com.happiness.happy.tire.entity.TireInfo;
import com.happiness.happy.tire.mapper.TireInRecordMapper;
import com.happiness.happy.tire.mapper.TireInfoMapper;
import com.happiness.happy.tire.server.TireInRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Slf4j
@Transactional
public class TireInRecordServiceImpl extends ServiceImpl<TireInRecordMapper, TireInRecord> implements TireInRecordService {
    @Autowired
    private TireInRecordMapper tireInRecordMapper;
    @Autowired
    private TireInfoMapper tireInfoMapper;

    @Override
    public Response tireInRecord(TireInRecord tireInRecord) {
        try {
            String tireId = tireInRecord.getTireId();
            synchronized (this) {
                TireInfo tireInfo = tireInfoMapper.selectById(tireId);
                if (Objects.nonNull(tireInfo)) {
                    Integer tireInfoTireNum = tireInfo.getTireNum();
                    Integer tireNum = tireInRecord.getTireNum();
                    int realNum = tireNum + tireInfoTireNum;
                    if (realNum >= 0) {
                        TireInfo updateData = new TireInfo();
                        updateData.setTireId(tireId);
                        updateData.setTireNum(realNum);
                        int i = tireInfoMapper.updateById(updateData);
                        log.info("更新轮胎ID<{}>条数成功!更新的数据条数<{}>", tireId, i);
                    }
                } else {
                    log.warn("未找到该轮胎ID<{}>", tireId);
                    tireInRecord.setRemark("未找到该轮胎ID:" + tireId);
                    int i = tireInRecordMapper.updateById(tireInRecord);
                    log.info("更新记录信息,添加备注成功.更新数据条数<{}>", i);
                }
                tireInRecord.setTireBand(tireInfo.getTireBand());
                tireInRecord.setTireSize(tireInfo.getTireSize());
                tireInRecord.setTirePattern(tireInfo.getTirePattern());
                tireInRecordMapper.insert(tireInRecord);
            }
            return Response.builder().code(200).msg("添加轮胎进货记录成").object(true).build();

        } catch (Exception e) {
            log.error("添加轮胎进货记录失败,失败详情: " + e);
            return Response.builder().code(500).msg("添加轮胎进货记录失败").build();
        }
    }

    @Override
    public Response getPage(QueryParams queryParams) {
        try {
            Page<TireInRecord> page = new Page<>(Objects.nonNull(queryParams.getPageNum()) ? queryParams.getPageNum() : 1, 10);
            QueryWrapper<TireInRecord> tireInRecordQueryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(queryParams.getStartTime())) {
                tireInRecordQueryWrapper.ge("CREATED_TIME", queryParams.getStartTime());
            }
            if (StringUtils.isNotBlank(queryParams.getEndTime())) {
                tireInRecordQueryWrapper.le("CREATED_TIME", queryParams.getEndTime());
            }
            Page<TireInRecord> tireInRecordPage = tireInRecordMapper.selectPage(page, tireInRecordQueryWrapper);
            return Response.builder().code(200).msg("入库记录查询成功").object(tireInRecordPage).build();
        }catch (Exception e){
            log.error("入库记录查询失败,失败详情: " + e);
            return Response.builder().code(500).msg("入库记录查询失败").build();
        }

    }

}
