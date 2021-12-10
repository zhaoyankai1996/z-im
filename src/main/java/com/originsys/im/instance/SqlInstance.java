package com.originsys.im.instance;

import com.originsys.im.dao.EimUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/12/3 9:44
 */
public class SqlInstance {
    /**
     * 构建私有实例 防止外部实例化
     */
    private SqlInstance(){

    }

    /**
     * 数据库连接实例
     */
    private static SqlInstance instance;

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 获取数据库初始化实例(用户类)
     * @return
     */
    public static SqlInstance getInstance(){
        if(instance == null){
            instance = new SqlInstance();
            String resource = "mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return instance;
    }

    /**
     * 获取用户操作sql类
     * @return
     */
    public EimUserMapper getUserMapper(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EimUserMapper mapper = sqlSession.getMapper(EimUserMapper.class);
        return mapper;
    }

}
