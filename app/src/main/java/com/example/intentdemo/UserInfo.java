package com.example.intentdemo;

import java.io.Serializable;

class UserInfo implements Serializable {
    private String user;
    private String pwd;
    private String isAdmin;

    public UserInfo(String user, String pwd, String isAdmin) {
        this.user = user;
        this.pwd = pwd;
        this.isAdmin = isAdmin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
