package com.happiness.happy.tire.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.TireInfo;
import com.happiness.happy.tire.mapper.TireInfoMapper;
import com.happiness.happy.tire.server.TireInfoService;
import com.happiness.happy.tire.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@Slf4j
public class TireInfoServiceImpl extends ServiceImpl<TireInfoMapper, TireInfo> implements TireInfoService {
    @Autowired
    private TireInfoMapper tireInfoMapper;

    @Override
    public Response addTireInfo(TireInfo tireInfo) {
        try {
            //新增轮胎信息，数量默认为0
            tireInfo.setTireNum(0);
            tireInfo.setTireId(CommonUtil.getUUID());
            tireInfoMapper.insert(tireInfo);
            return Response.builder().code(200).msg("新增轮胎信息成功").object(true).build();
        } catch (DuplicateKeyException duplicateKeyException) {
            log.error("该类型轮胎已存在,不可重复添加.异常详细信息:" + duplicateKeyException);
            return Response.builder().code(500).msg("该类型轮胎已存在,不可重复添加").build();
        } catch (Exception e) {
            log.error("新增轮胎信息失败!,失败信息:" + e);
            return Response.builder().code(500).msg("新增轮胎信息失败").build();
        }
    }

    @Override
    public Response updateTireInfo(TireInfo tireInfo) {
        UpdateWrapper<TireInfo> tireInfoUpdateWrapper = new UpdateWrapper<>();
        if (StringUtils.isBlank(tireInfo.getTireId())) {
            log.error("轮胎ID为空,无法更新数据!");
            return Response.builder().code(500).msg("轮胎ID为空,无法更新数据!").build();
        }
        tireInfoUpdateWrapper.eq("TIRE_ID", tireInfo.getTireId());
        try {
            tireInfoMapper.update(tireInfo, tireInfoUpdateWrapper);
            return Response.builder().code(200).msg("更新轮胎信息成功").object(true).build();

        } catch (DuplicateKeyException duplicateKeyException) {
            log.error("该类型轮胎已存在,无法更新.异常详细信息:" + duplicateKeyException);
            return Response.builder().code(500).msg("该类型轮胎已存在,无法更新").build();

        } catch (Exception e) {
            log.error("更新轮胎信息失败!,失败信息:" + e);
            return Response.builder().code(500).msg("更新轮胎信息失败").build();
        }
    }

    @Override
    public Response getPage(TireInfo tireInfo, Integer pageNum, Integer size) {
        try {
            Page<TireInfo> page = new Page<>(Objects.isNull(pageNum) || pageNum == 0 ? 1 : pageNum,
                    Objects.isNull(size) || size == 0 ? 10 : size);
            QueryWrapper<TireInfo> tireInfoQueryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(tireInfo.getTireBand())) {
                tireInfoQueryWrapper.like("TIRE_BAND", tireInfo.getTireBand()).or();
            }
            if (StringUtils.isNotBlank(tireInfo.getTireSize())) {
                tireInfoQueryWrapper.like("TIRE_SIZE", tireInfo.getTireSize()).or();
            }
            if (StringUtils.isNotBlank(tireInfo.getTirePattern())) {
                tireInfoQueryWrapper.like("TIRE_PATTERN", tireInfo.getTirePattern()).or();
            }
            Page<TireInfo> tireInfoPage = tireInfoMapper.selectPage(page, tireInfoQueryWrapper);
            return Response.builder().code(200).msg("查询轮胎分页信息成功").object(tireInfoPage).build();
        } catch (Exception e) {
            log.error("查询轮胎分页信息失败,失败信息: " + e);
            return Response.builder().code(500).msg("查询轮胎分页信息失败").build();
        }
    }
}
