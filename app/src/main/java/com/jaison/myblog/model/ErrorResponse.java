package com.jaison.myblog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 11/03/17.
 */

public class ErrorResponse {

    @SerializedName("error")
    String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
