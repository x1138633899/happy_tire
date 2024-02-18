package com.happiness.happy.tire.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends Other implements Serializable {
    @ExcelProperty(value = "用户ID")
    @TableId
    private String userId;
    @ExcelProperty(value = "用户名")
    @TableField(value = "userName")
    private String userName;
    @TableField(value = "password", select = false)
    @ExcelIgnore
    private String password;
    @ExcelProperty(value = "手机号")
    @TableField(value = "phoneNum")
    private String phoneNum;
    @ExcelProperty(value = "姓名")
    @TableField(value = "name")
    private String name;
    @ExcelProperty(value = "是否可用")
    @TableField(value = "is_usable")
    private String usable;
}
