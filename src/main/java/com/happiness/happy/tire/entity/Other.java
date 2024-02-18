package com.happiness.happy.tire.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Other {
    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer size;
}
