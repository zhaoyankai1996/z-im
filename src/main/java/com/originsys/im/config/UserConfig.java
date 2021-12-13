package com.originsys.im.config;

import com.originsys.im.domain.EimUser;
import com.originsys.im.domain.ResultData;

/**
 * 用户自定义逻辑类
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/12/3 9:28
 */
public interface UserConfig {
    /**
     * 用户自定义获取token
     * @param user 返回给用户查询出的im用户信息
     * @return 用户自定义返回ResultData
     */
    ResultData getToken(EimUser user);
}
