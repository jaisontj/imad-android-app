package com.jaison.myblog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 11/03/17.
 */

public class MessageResponse {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
