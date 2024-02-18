package com.happiness.happy.tire.web;

import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.server.UserInfoService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//@RestController
public class UserController {
    @Resource
    private UserInfoService userInfoService;

    public Response login(String userName, String password) {
        return userInfoService.login(userName, password);
    }
}
