package com.example.newsapi.Retro;

import com.example.newsapi.model.NewsEverythingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apis {

@GET("top-headlines?country=in&category=business&apiKey=ac500ae75cdf4f769c5a64f5ebd785a1")
    Call<NewsEverythingModel> newsEverythingCall();


    @GET("top-headlines?country=in&category=business&apiKey=ac500ae75cdf4f769c5a64f5ebd785a1")
    Call<NewsEverythingModel> newsEverythingCallpagination(@Query("page") int page);


}
