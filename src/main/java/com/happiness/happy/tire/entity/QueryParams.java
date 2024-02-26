package com.happiness.happy.tire.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class QueryParams {
    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer size;
}
