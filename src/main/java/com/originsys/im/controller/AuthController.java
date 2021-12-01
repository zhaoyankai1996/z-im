package com.originsys.im.controller;

import com.originsys.im.domain.EimUser;
import com.originsys.im.domain.ResultData;
import com.originsys.im.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/26 14:56
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/token")
    public ResultData authUser(String user_id,String pwd){
        EimUser eimUser = new EimUser();
        eimUser.setUser_id(user_id);
        eimUser.setPwd(pwd);
        EimUser userByData = authService.getUserByData(eimUser);
        ResultData resultData = new ResultData();
        if(userByData != null && userByData.getId() != null){
            resultData.setCode(1);
            resultData.setData("授权成功");
        }else{
            resultData.setCode(-1);
            resultData.setData("授权失败");
        }
        return resultData;
    }
}
