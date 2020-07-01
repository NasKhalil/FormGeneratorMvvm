package com.example.formgenerator.inscription;

import com.example.formgenerator.model.User;

public class InscriptionResponse {

    private boolean result = false ;
    private  String msg ;
    private User mUser = new User();

    public InscriptionResponse(boolean result, String msg, User mUser) {
        this.result = result;
        this.msg = msg;
        this.mUser = mUser;
    }

    public InscriptionResponse(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
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
