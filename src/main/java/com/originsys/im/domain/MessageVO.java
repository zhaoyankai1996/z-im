package com.originsys.im.domain;

import java.util.Date;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 19:04
 */
public class MessageVO {
    /**
     * 消息状态码
     */
    private Integer code;
    /**
     * 消息来源
     */
    private String from;
    /**
     * 消息归处
     */
    private String to;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 发送时间
     */
    private Date send_date;
    /**
     * 是否接受
     */
    private Integer is_receive;
    /**
     * 是否群组
     */
    private Integer is_group;
    /**
     * 会话id
     */
    private Integer session_id;
    /**
     * 消息类型
     */
    private Integer msg_type;
    /**
     * 扩展字段
     */
    private String extras;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }

    public Integer getIs_receive() {
        return is_receive;
    }

    public void setIs_receive(Integer is_receive) {
        this.is_receive = is_receive;
    }

    public Integer getIs_group() {
        return is_group;
    }

    public void setIs_group(Integer is_group) {
        this.is_group = is_group;
    }

    public Integer getSession_id() {
        return session_id;
    }

    public void setSession_id(Integer session_id) {
        this.session_id = session_id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(Integer msg_type) {
        this.msg_type = msg_type;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "code=" + code +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msg='" + msg + '\'' +
                ", send_date=" + send_date +
                ", is_receive=" + is_receive +
                ", is_group=" + is_group +
                ", session_id=" + session_id +
                ", msg_type=" + msg_type +
                ", extras='" + extras + '\'' +
                '}';
    }
}
