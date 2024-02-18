package com.happiness.happy.tire.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.TireInRecord;
import com.happiness.happy.tire.mapper.TireInRecordMapper;
import com.happiness.happy.tire.server.TireInRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional
public class TireInRecordServiceImpl extends ServiceImpl<TireInRecordMapper, TireInRecord> implements TireInRecordService {
    @Autowired
    private TireInRecordMapper tireInRecordMapper;

    @Override
    public Response tireInRecord(TireInRecord tireInRecord) {
        try {
            tireInRecordMapper.insert(tireInRecord);
            return Response.builder().code(200).msg("添加轮胎进货记录成").object(true).build();
        } catch (Exception e) {
            log.error("添加轮胎进货记录失败,失败详情: " + e);
            return Response.builder().code(500).msg("添加轮胎进货记录失败").build();
        }
    }

    @Override
    public Response getPage(TireInRecord tireInRecord, int pageNum, int size) {
        return null;
    }
}
