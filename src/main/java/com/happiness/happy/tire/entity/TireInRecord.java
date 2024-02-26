package com.happiness.happy.tire.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("tb_tire_in_record")
@EqualsAndHashCode(callSuper = true)
public class TireInRecord extends QueryParams implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ExcelProperty(value = "轮胎品牌")
    private String tireBand;
    @ExcelProperty(value = "轮胎型号")
    private String tireSize;
    @ExcelProperty(value = "轮胎花纹")
    private String tirePattern;
    @ExcelProperty(value = "轮胎ID")
    @NotNull(message = "轮胎ID不能为空")
    private String tireId;
    @ExcelProperty(value = "轮胎数量")
    @NotNull(message = "轮胎数量不能为空")
    private Integer tireNum;
    @ExcelProperty(value = "创建人")
    @NotNull(message = "创建人不能为空")
    private String createdBy;
    @ExcelProperty(value = "创建时间")
    private Timestamp createdTime;
    @ExcelProperty(value = "备注")
    private String remark;
}
