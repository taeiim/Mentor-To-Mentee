package com.example.parktaeim.mentor_to_mentee.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWTModel {
    @SerializedName("Authorization")
    @Expose
    private String authorization;

    public JWTModel(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }
}
