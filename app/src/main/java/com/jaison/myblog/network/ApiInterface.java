package com.jaison.myblog.network;

import com.jaison.myblog.model.Article;
import com.jaison.myblog.model.AuthenticationRequest;
import com.jaison.myblog.model.MessageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by jaison on 11/03/17.
 */

public interface ApiInterface {

    @POST(NetworkURL.LOGIN)
    Call<MessageResponse> login(@Body AuthenticationRequest body);

    @POST(NetworkURL.REGISTERATION)
    Call<MessageResponse> registration(@Body AuthenticationRequest body);

    @GET(NetworkURL.GET_ARTICLES)
    Call<List<Article>> getArticles();

    @GET(NetworkURL.LOGOUT)
    Call<Void> logout();

}
