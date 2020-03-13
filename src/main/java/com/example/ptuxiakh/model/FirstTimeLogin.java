package com.example.ptuxiakh.model;

public class FirstTimeLogin {
    Boolean firstLogin;

    public FirstTimeLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public FirstTimeLogin() {
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
