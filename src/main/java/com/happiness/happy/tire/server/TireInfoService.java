package com.happiness.happy.tire.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.TireInfo;

public interface TireInfoService extends IService<TireInfo> {
    /**
     * 新增轮胎信息
     *
     * @param tireInfo 轮胎信息
     * @return 是否成功
     */
    Response addTireInfo(TireInfo tireInfo);

    /**
     * 更新轮胎信息
     *
     * @param tireInfo 轮胎信息
     * @return 是否成功
     */
    Response updateTireInfo(TireInfo tireInfo);

    /**
     * 分页查询轮胎信息
     *
     * @param tireInfo
     * @param pageNum
     * @param size
     * @return
     */
    Response getPage(TireInfo tireInfo, Integer pageNum, Integer size);

}
