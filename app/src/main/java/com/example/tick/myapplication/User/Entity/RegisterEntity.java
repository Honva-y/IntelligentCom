package com.example.tick.myapplication.User.Entity;

/**
 * Created by Tick on 2016/9/27.
 */
public class RegisterEntity {
    String user_account;
    String user_password;
    int code;

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
