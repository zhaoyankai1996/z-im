package com.originsys.im.domain;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/25 18:51
 */
public class PreCheckVO {
    private boolean state;
    private String msg;

    public PreCheckVO() {
    }

    public PreCheckVO(boolean state) {
        this.state = state;
    }


    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PreCheckVO{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                '}';
    }
}
