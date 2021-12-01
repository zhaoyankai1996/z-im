package com.originsys.im.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.originsys.im.config.Const;
import com.originsys.im.config.ServerConfig;
import com.originsys.im.domain.MessageVO;
import com.originsys.im.domain.PreCheckVO;
import com.originsys.im.util.BaseAction;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.Cookie;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 15:44
 */
public class WsMsgHandler extends BaseAction implements IWsMsgHandler {
//    public static final ShowcaseWsMsgHandler me = new ShowcaseWsMsgHandler();
//    public static final WsMsgHandler me = new WsMsgHandler();
    /**
     * 握手时走这个方法，业务可以在这里获取cookie，request参数等
     * @param httpRequest
     * @param httpResponse
     * @param channelContext
     * @return
     * @throws Exception
     */
    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
//        List<Cookie> cookies = httpRequest.getCookies();
//        log().info(cookies.size());
//        for (Cookie cookie : cookies) {
//            log().info(cookie.getName()+":"+cookie.getValue());
//        }
        String clientip = httpRequest.getClientIp();
        String userId = httpRequest.getParam("userId");
        if(!connectionPreCheck(httpRequest,httpResponse).getState()){
            return httpResponse;
        }else{
            Tio.bindUser(channelContext, userId);
//        channelContext.setUserid(myname);
            log().info("收到来自"+ URLDecoder.decode(clientip,"UTF-8") +"的ws握手包\r\n"+URLDecoder.decode(httpRequest.toString(),"UTF-8"));
        }
        return httpResponse;
    }

    /**
     * 建立连接后，可以用来加入群组
     * @param httpRequest
     * @param httpResponse
     * @param channelContext
     * @throws Exception
     */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        log().info("onAfterHandshaked:");
//        List<Cookie> cookies = httpRequest.getCookies();
//        for (Cookie cookie : cookies) {
//            log().info(cookie.getName()+":"+cookie.getValue());
//        }
        String userId = httpRequest.getParam("userId");
        log().info(Tio.getByUserid(channelContext.tioConfig,userId) == null);
        PreCheckVO preCheckVO = connectionPreCheck(httpRequest,httpResponse);
        if(!preCheckVO.getState()){
//            String msg = "{name:'admin',message:'" + channelContext.userid + " 进来了，共【" + 1 + "】人在线" + "'}";
            MessageVO messageVO = new MessageVO();
            messageVO.setFrom("admin");
            messageVO.setTo(userId);
            messageVO.setMsg(preCheckVO.getMsg());
            messageVO.setIs_group(0);
            messageVO.setIs_receive(0);
            messageVO.setSend_date(new Date());
            messageVO.setCode(-1);
            String message = JSONObject.toJSONString(messageVO);
            WsResponse wsResponse = WsResponse.fromText(message, ServerConfig.CHARSET);
            Tio.bSend(channelContext,wsResponse);
            Tio.remove(channelContext,"预检查未通过");
            return;
        }
//        //绑定到群组，后面会有群发
//        Tio.bindGroup(channelContext, Const.GROUP_ID);
//        int count = Tio.getAll(channelContext.tioConfig).getObj().size();
//        String msg = "{name:'admin',message:'" + channelContext.userid + " 进来了，共【" + count + "】人在线" + "'}";
//        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
//        WsResponse wsResponse = WsResponse.fromText(msg, ShowcaseServerConfig.CHARSET);
//        //群发
//        Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);
    }

    /**
     * 字节消息（binaryType = arraybuffer）过来后会走这个方法
     */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    /**
     * 当客户端发close flag时，会走这个方法
     */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        log().info("onClose:");
        Tio.remove(channelContext, "receive close flag");
        afterClose(wsRequest,bytes,channelContext);
        return null;
    }

    /*
     * 字符消息（binaryType = blob）过来后会走这个方法
     */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.get();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequest();//获取websocket握手包
        if (log().isDebugEnabled()) {
            log().debug("握手包:"+httpRequest);
        }
        log().info("收到ws消息:"+text);
        if (Objects.equals("心跳内容", text)) {
            return null;
        }
        MessageVO messageVO = JSONObject.parseObject(text, MessageVO.class);
        messageVO.setCode(1);
        //channelContext.getToken()
        //String msg = channelContext.getClientNode().toString() + " 说：" + text;
//        String msg = "{name:'" + channelContext.userid + "',message:'" + text + "'}";
        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
        WsResponse wsResponse = WsResponse.fromText(JSONObject.toJSONString(messageVO), ServerConfig.CHARSET);
        Tio.sendToUser(channelContext.tioConfig,messageVO.getTo(),wsResponse);
//        //群发
//        Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);
        //返回值是要发送给客户端的内容，一般都是返回null
        return null;
    }

    /**
     * 建立连接以及建立连接成功后的预检查
     * @return
     */
    public PreCheckVO connectionPreCheck(HttpRequest httpRequest, HttpResponse httpResponse){
        return new PreCheckVO(true);
    }

    public void afterClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext){

    }
}
