package com.happiness.happy.tire.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.UserInfo;

public interface UserInfoService extends IService<UserInfo> {
    /**
     * 用户注册
     *
     * @param userName 用户名/手机号
     * @param password 密码
     * @return 是否成功
     */
    Response register(String userName, String password);

    /**
     * 用户登录
     *
     * @param userName 用户名/手机号
     * @param password 密码
     * @return 用户信息
     */
    Response login(String userName, String password);

    /**
     * 修改密码
     *
     * @param userName    用户名/手机号
     * @param password    旧密码
     * @param newPassword 新密码
     * @return 是否成功
     */
    Response modPwd(String userName, String password, String newPassword);

    /**
     * 登出
     *
     * @param userName 用户名/手机号
     * @return 是否成功
     */
    Response logOut(String userName);
}
