package com.originsys.im.domain;

import java.util.Date;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 19:04
 */
public class MessageVO {
    private String from;
    private String to;
    private String msg;
    private Date send_date;
    private Integer is_receive;
    private Integer is_group;
    private Integer session_id;

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

    @Override
    public String toString() {
        return "MessageVO{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msg='" + msg + '\'' +
                ", send_date='" + send_date + '\'' +
                ", is_receive=" + is_receive +
                ", is_group=" + is_group +
                ", session_id=" + session_id +
                '}';
    }
}
