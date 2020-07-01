package com.example.formgenerator.login;

import com.example.formgenerator.model.User;

public class LoginResponse {
    private boolean result = false ;
    private  String msg ;
    private User mUser = new User();

    public LoginResponse(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public LoginResponse(boolean result, String msg, User mUser) {
        this.result = result;
        this.msg = msg;
        this.mUser = mUser;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
