package com.originsys.im.util;

import org.apache.log4j.Logger;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 15:57
 */
public class BaseAction {
    protected Logger log(){
        return Logger.getLogger(this.getClass().getName());
    }
}
