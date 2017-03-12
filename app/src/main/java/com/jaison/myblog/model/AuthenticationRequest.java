package com.jaison.myblog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 11/03/17.
 */

public class AuthenticationRequest {

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
