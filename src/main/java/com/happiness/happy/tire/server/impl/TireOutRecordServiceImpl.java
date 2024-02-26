package com.happiness.happy.tire.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happiness.happy.tire.entity.*;
import com.happiness.happy.tire.mapper.TireInfoMapper;
import com.happiness.happy.tire.mapper.TireOutRecordMapper;
import com.happiness.happy.tire.server.TireOutRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Slf4j
@Transactional
public class TireOutRecordServiceImpl extends ServiceImpl<TireOutRecordMapper, TireOutRecord> implements TireOutRecordService {
    @Autowired
    private TireOutRecordMapper tireOutRecordMapper;
    @Autowired
    private TireInfoMapper tireInfoMapper;

    @Override
    public Response tireOutRecord(TireOutRecord tireOutRecord) {
        try {
            String tireId = tireOutRecord.getTireId();
            synchronized (this) {
                TireInfo tireInfo = tireInfoMapper.selectById(tireId);
                if (Objects.nonNull(tireInfo)) {
                    Integer tireInfoTireNum = tireInfo.getTireNum();
                    Integer tireNum = tireOutRecord.getTireNum();
                    int realNum = tireInfoTireNum - tireNum;
                    if (realNum >= 0) {
                        TireInfo updateData = new TireInfo();
                        updateData.setTireId(tireId);
                        updateData.setTireNum(realNum);
                        int i = tireInfoMapper.updateById(updateData);
                        log.info("更新轮胎ID<{}>条数成功!更新的数据条数<{}>", tireId, i);
                    }
                } else {
                    log.warn("未找到该轮胎ID<{}>", tireId);
                    tireOutRecord.setRemark("未找到该轮胎ID:" + tireId);
                    int i = tireOutRecordMapper.updateById(tireOutRecord);
                    log.info("更新记录信息,添加备注成功.更新数据条数<{}>", i);
                }
                tireOutRecord.setTireBand(tireInfo.getTireBand());
                tireOutRecord.setTireSize(tireInfo.getTireSize());
                tireOutRecord.setTirePattern(tireInfo.getTirePattern());
                tireOutRecordMapper.insert(tireOutRecord);
            }
            return Response.builder().code(200).msg("添加轮胎销售记录成").object(true).build();
        } catch (Exception e) {
            log.error("添加轮胎进货记录失败,失败详情: " + e);
            return Response.builder().code(500).msg("添加轮胎销售记录失败").build();
        }
    }

    @Override
    public Response getPage(QueryParams queryParams) {
        try {
            Page<TireOutRecord> page = new Page<>(Objects.nonNull(queryParams.getPageNum()) ? queryParams.getPageNum() : 1, 10);
            QueryWrapper<TireOutRecord> tireInRecordQueryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(queryParams.getStartTime())) {
                tireInRecordQueryWrapper.ge("CREATED_TIME", queryParams.getStartTime());
            }
            if (StringUtils.isNotBlank(queryParams.getEndTime())) {
                tireInRecordQueryWrapper.le("CREATED_TIME", queryParams.getEndTime());
            }
            Page<TireOutRecord> tireInRecordPage = tireOutRecordMapper.selectPage(page, tireInRecordQueryWrapper);
            return Response.builder().code(200).msg("出库记录查询成功").object(tireInRecordPage).build();
        } catch (Exception e) {
            log.error("出库记录查询失败,失败详情: " + e);
            return Response.builder().code(500).msg("出库记录查询失败").build();
        }
    }

}
