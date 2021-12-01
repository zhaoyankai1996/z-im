package com.originsys.im.config;

import org.apache.log4j.Logger;
import org.tio.utils.time.Time;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 16:02
 */
public class ServerConfig {
    private static Logger log = Logger.getLogger(ServerConfig.class);
    private static Properties properties;
    private static ServerConfig serverConfig = new ServerConfig();
    /**
     * 协议名字(可以随便取，主要用于开发人员辨识)
     */
    public static String PROTOCOL_NAME = "eap5";
    public static String CHARSET = "utf-8";
    /**
     * 监听的ip
     */
    public static String SERVER_IP = null;//null表示监听所有，并不指定ip
    /**
     * 监听端口
     */
    public static int SERVER_PORT = 9326;
    /**
     * 心跳超时时间，单位：毫秒
     */
    public static int HEARTBEAT_TIMEOUT = 1000 * 60;
    /**
     * ip数据监控统计，时间段
     * @author tanyaowu
     *
     */
    public static interface IpStatDuration {
        public static final Long DURATION_1 = Time.MINUTE_1 * 5;
        public static final Long[] IPSTAT_DURATIONS = new Long[] { DURATION_1 };
    }

    public static ServerConfig getInstance(){
        return serverConfig;
    }

    public void useProp(String fileName){
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("Properties file not found in classpath: " + fileName);
            }
            properties = new Properties();
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
            setProtocolName(properties.getProperty("PROTOCOL_NAME"));
            setCHARSET(properties.getProperty("CHARSET"));
            setHeartbeatTimeout(Integer.valueOf(properties.getProperty("HEARTBEAT_TIMEOUT")));
            setServerPort(Integer.valueOf(properties.getProperty("SERVER_PORT")));
        } catch (IOException var12) {
            throw new RuntimeException("Error loading properties file.", var12);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var11) {
                    log.error(var11.getMessage(), var11);
                }
            }

        }
    }

    public static String getProtocolName() {
        return PROTOCOL_NAME;
    }

    public static void setProtocolName(String protocolName) {
        PROTOCOL_NAME = protocolName;
    }

    public static String getCHARSET() {
        return CHARSET;
    }

    public static void setCHARSET(String CHARSET) {
        ServerConfig.CHARSET = CHARSET;
    }

    public static String getServerIp() {
        return SERVER_IP;
    }

    public static void setServerIp(String serverIp) {
        SERVER_IP = serverIp;
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }

    public static void setServerPort(int serverPort) {
        SERVER_PORT = serverPort;
    }

    public static int getHeartbeatTimeout() {
        return HEARTBEAT_TIMEOUT;
    }

    public static void setHeartbeatTimeout(int heartbeatTimeout) {
        HEARTBEAT_TIMEOUT = heartbeatTimeout;
    }
}
