package com.sesong.combeenation;

import android.app.Application;

public class TokenData extends Application {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
