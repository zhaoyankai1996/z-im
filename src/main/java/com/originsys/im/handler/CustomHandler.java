package com.originsys.im.handler;

import com.originsys.im.domain.PreCheckVO;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 18:18
 */
public class CustomHandler extends WsMsgHandler {
    @Override
    public PreCheckVO connectionPreCheck(HttpRequest httpRequest, HttpResponse httpResponse) {
        PreCheckVO preCheckVO = new PreCheckVO(true);
        preCheckVO.setMsg("预检查未通过");
        return preCheckVO;
    }
}
