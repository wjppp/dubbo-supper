package com.jk.entity;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = -7040606105029392576L;

        private  Integer   userId;
        private  String    userName;
        private  String    userPass;

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
