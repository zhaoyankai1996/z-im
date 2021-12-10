package com.originsys.im.domain;

public class EimUser {
    private Long id;

    private String user_id;

    private String pwd;

    private String nick_name;

    private String expand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name == null ? null : nick_name.trim();
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand == null ? null : expand.trim();
    }

    @Override
    public String toString() {
        return "EimUser{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", expand='" + expand + '\'' +
                '}';
    }
}