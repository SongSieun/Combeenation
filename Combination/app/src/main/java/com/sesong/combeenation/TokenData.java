package com.sesong.combeenation;

import android.app.Application;

public class TokenData {
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private static TokenData instance;

    static {
        instance = new TokenData();
    }

    private TokenData() {
    }

    public static synchronized TokenData getInstance() {
        if (null == instance) {
            instance = new TokenData();
        }

        return instance;

    }
}