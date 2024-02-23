package com.example.appnews.apis

import com.example.appnews.constants
import com.example.appnews.model.soursesResponce.NewsResponse.NewsResponse
import com.example.appnews.model.soursesResponce.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("v2/top-headlines/sources")
    fun getNewsRequest(@Query("apiKey") apiKey:String=constants.apikey):Call<SourcesResponse>


      @GET("/v2/everything")
    fun getNews(
          @Query("apiKey") apiKey:String=constants.apikey,
          @Query("sources") sources:String  ) :Call<NewsResponse>




}