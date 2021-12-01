package com.originsys.im;

import com.originsys.im.config.ServerConfig;
import com.originsys.im.handler.CustomHandler;
import com.originsys.im.handler.WsMsgHandler;
import com.originsys.im.listener.CustomServerAioListener;
import com.originsys.im.listener.ServerAioListener;
import org.tio.server.ServerTioConfig;
import org.tio.utils.jfinal.P;
import org.tio.websocket.server.WsServerStarter;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 16:27
 */
public class ShowcaseWebsocketStarter {
    private WsServerStarter wsServerStarter;
    private ServerTioConfig serverTioConfig;
    public ShowcaseWebsocketStarter(int port, WsMsgHandler wsMsgHandler,ServerAioListener serverAioListener) throws Exception {
        wsServerStarter = new WsServerStarter(port, wsMsgHandler);
        serverTioConfig = wsServerStarter.getServerTioConfig();
        serverTioConfig.setName(ServerConfig.PROTOCOL_NAME);
        serverTioConfig.setServerAioListener(serverAioListener);
        //设置ip监控
//        serverTioConfig.setIpStatListener(ShowcaseIpStatListener.me);
        //设置ip统计时间段
//        serverTioConfig.ipStats.addDurations(ShowcaseServerConfig.IpStatDuration.IPSTAT_DURATIONS);
        //设置心跳超时时间
        serverTioConfig.setHeartbeatTimeout(ServerConfig.HEARTBEAT_TIMEOUT);
//        if (P.getInt("ws.use.ssl", 1) == 1) {
//            //如果你希望通过wss来访问，就加上下面的代码吧，不过首先你得有SSL证书（证书必须和域名相匹配，否则可能访问不了ssl）
////            String keyStoreFile = "classpath:config/ssl/keystore.jks";
////            String trustStoreFile = "classpath:config/ssl/keystore.jks";
////            String keyStorePwd = "214323428310224";
//            String keyStoreFile = P.get("ssl.keystore", null);
//            String trustStoreFile = P.get("ssl.truststore", null);
//            String keyStorePwd = P.get("ssl.pwd", null);
//            serverTioConfig.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
//        }
    }
    public static void start(WsMsgHandler wsMsgHandler,ServerAioListener serverAioListener) throws Exception {
        ShowcaseWebsocketStarter appStarter = appStarter = new ShowcaseWebsocketStarter(ServerConfig.SERVER_PORT, wsMsgHandler,serverAioListener);
        appStarter.wsServerStarter.start();
    }
    public static void start() throws Exception {
        ShowcaseWebsocketStarter appStarter = new ShowcaseWebsocketStarter(ServerConfig.SERVER_PORT, new WsMsgHandler(),new ServerAioListener());
        appStarter.wsServerStarter.start();
    }
    /**
     * @return the serverTioConfig
     */
    public ServerTioConfig getServerTioConfig() {
        return serverTioConfig;
    }
    public WsServerStarter getWsServerStarter() {
        return wsServerStarter;
    }
    public static void main(String[] args) throws Exception {
        //启动websocket server
        ServerConfig.getInstance().useProp("im.properties");
        start(new CustomHandler(),new CustomServerAioListener());
    }
}
