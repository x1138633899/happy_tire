package com.happiness.happy.tire.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("tb_tire_info")
@EqualsAndHashCode(callSuper = true)
public class TireInfo extends Other implements Serializable {
    @TableId
    @ExcelProperty(value = "轮胎ID")
    private String tireId;
    @NotNull(message = "轮胎品牌不能为空")
    @ExcelProperty(value = "轮胎品牌")
    private String tireBand;
    @NotNull(message = "轮胎型号不能为空")
    @ExcelProperty(value = "轮胎型号")
    private String tireSize;
    @NotNull(message = "轮胎花纹不能为空")
    @ExcelProperty(value = "轮胎花纹")
    private String tirePattern;
    @ExcelProperty(value = "轮胎数量")
    private Integer tireNum;
    @NotNull(message = "创建人不能为空")
    @ExcelProperty(value = "创建人")
    private String createdBy;
    @ExcelProperty(value = "创建时间")
    private Timestamp createdTime;
    @ExcelProperty(value = "更新人")
    private String updatedBy;
    @ExcelProperty(value = "更新时间")
    private Timestamp updatedTime;
    @ExcelProperty(value = "备注")
    private String remark;
}
