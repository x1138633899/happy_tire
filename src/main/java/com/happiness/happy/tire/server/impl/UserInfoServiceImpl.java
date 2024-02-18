package com.happiness.happy.tire.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.UserInfo;
import com.happiness.happy.tire.mapper.UserInfoMapper;
import com.happiness.happy.tire.server.UserInfoService;
import com.happiness.happy.tire.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public Response register(String userName, String password) {
        return null;
    }

    @Override
    public Response login(String userName, String password) {
        try {
            QueryWrapper<UserInfo> userInfoWrapper = new QueryWrapper<>();
            userInfoWrapper.eq("USER_NAME", userName);
            userInfoWrapper.eq("PASSWORD", password);
            UserInfo userInfo = getOne(userInfoWrapper, true);
            String token = JwtUtil.createToken(userInfo);
            return Response.builder().code(200).msg("登录成功").object(token).build();
        } catch (Exception e) {
            log.error("用户<{}>登录失败! 异常信息:<{}>", userName, e);
            return Response.builder().code(500).msg("登录失败").build();
        }

    }

    @Override
    public Response modPwd(String userName, String password, String newPassword) {
        return null;
    }

    @Override
    public Response logOut(String userName) {
        return null;
    }
}
