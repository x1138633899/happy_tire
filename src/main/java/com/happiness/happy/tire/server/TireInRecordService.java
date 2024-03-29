package com.happiness.happy.tire.server;


import com.baomidou.mybatisplus.extension.service.IService;
import com.happiness.happy.tire.entity.QueryParams;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.TireInRecord;

public interface TireInRecordService extends IService<TireInRecord> {
    /**
     * 轮胎进货记录
     *
     * @param tireInRecord 进货实体类
     * @return 是否成功
     */
    Response tireInRecord(TireInRecord tireInRecord);

    /**
     * 分页查询轮胎进货记录
     *
     * @param queryParams 查询参数
     * @return
     */
    Response getPage(QueryParams queryParams);
}
