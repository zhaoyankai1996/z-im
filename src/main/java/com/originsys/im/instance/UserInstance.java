package com.originsys.im.instance;

import com.originsys.im.config.UserConfig;
import com.originsys.im.dao.EimUserMapper;
import com.originsys.im.domain.EimUser;
import com.originsys.im.domain.ResultData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用户实例类
 * 登录退出新增修改删除等用户操作
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/12/3 9:17
 */
public class UserInstance {

    /**
     * 构建私有实例 防止外部实例化
     */
    private UserInstance(){

    }

    /**
     * 用户实例
     */
    private static UserInstance instance;

    /**
     * 获取用户实例
     * @return
     */
    public static UserInstance getInstance(){
        if(instance == null){
            instance = new UserInstance();
        }
        return instance;
    }

    /**
     * 根据用户名密码获取用户
     * @param user
     * @return
     * @throws IOException
     */
    public EimUser getUser(EimUser user){
        EimUser eimUser = null;
        EimUserMapper mapper =  SqlInstance.getInstance().getUserMapper();
        eimUser = mapper.selectByUserData(user);
        return eimUser;
    }

    /**
     * 用户登录获取cookie操作
     * @param user 查询实体类
     * @param userConfig 用户自定义获取cookie操作
     * @return 返回token
     */
    public ResultData loginUser(EimUser user, UserConfig userConfig){
        //先通过用户名密码获取用户
        EimUser user1 = getUser(user);
        ResultData token = userConfig.getToken(user1);
        return token;
    }

}
