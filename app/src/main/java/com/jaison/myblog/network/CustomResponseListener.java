package com.jaison.myblog.network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jaison.myblog.model.ErrorResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CustomResponseListener<T> implements Callback<T> {

    public abstract void onSuccessfulResponse(T response);

    public abstract void onFailureResponse(ErrorResponse errorResponse);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccessfulResponse(response.body());
        } else {
            try {
                String errorMessage = response.errorBody().string();
                try {
                    ErrorResponse errorResponse = new Gson().fromJson(errorMessage, ErrorResponse.class);
                    onFailureResponse(errorResponse);
                } catch (JsonSyntaxException jsonException) {
                    jsonException.printStackTrace();
                    onFailureResponse(new ErrorResponse("Something went wrong"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                onFailureResponse(new ErrorResponse("Something went wrong"));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailureResponse(new ErrorResponse("Something went wrong"));
    }
}