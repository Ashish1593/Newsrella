package com.example.ashish.newsrella.NetworkUtils;

/**
 * Created by ashish on 18/12/17.
 */

import com.example.ashish.newsrella.Business.NewsSourcesResponse;
import com.example.ashish.newsrella.Business.TopHeadlinesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //for fetching news sources
    @GET("sources?language=en&country=us")
    Call<NewsSourcesResponse> getNewsSources(@Query("apikey") String apiKey);

    //for fetching top headlines of given news sources
    @GET("top-headlines")
    Call<TopHeadlinesResponse> getTopHeadlines(@Query("sources") String sources, @Query("apikey")
            String apiKey);
}