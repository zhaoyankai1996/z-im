package com.originsys.im.service;

import com.originsys.im.dao.EimUserMapper;
import com.originsys.im.domain.EimUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/26 16:30
 */
@Service
public class AuthService {

    @Autowired
    public EimUserMapper eimUserMapper;

    public EimUser getUserByData(EimUser eimUser){
        return eimUserMapper.selectByUserData(eimUser);
    }
}
