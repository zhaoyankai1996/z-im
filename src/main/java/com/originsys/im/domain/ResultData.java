package com.originsys.im.domain;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/11/26 16:45
 */
public class ResultData<T> {
    private int code;
    private String msg;
    private T t;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return t;
    }

    public void setData(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", t=" + t +
                '}';
    }
}
