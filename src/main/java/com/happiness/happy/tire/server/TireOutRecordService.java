package com.happiness.happy.tire.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.TireOutRecord;

public interface TireOutRecordService extends IService<TireOutRecord> {
    /**
     * 轮胎销售记录
     *
     * @param tireOutRecord 轮胎销售实体类
     * @return
     */
    public Response tireOutRecord(TireOutRecord tireOutRecord);

    /**
     * 分页查询轮胎销售记录
     *
     * @param tireOutRecord
     * @param pageNum
     * @param size
     * @return
     */
    Response getPage(TireOutRecord tireOutRecord, int pageNum, int size);

}
