package com.example.parktaeim.mentor_to_mentee.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWTModel {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("refreshToken")
    private String refreshToken;

    public JWTModel(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
