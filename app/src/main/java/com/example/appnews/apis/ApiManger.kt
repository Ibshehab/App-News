package com.example.appnews.apis

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiManger {

    private var retrofit: Retrofit
init {


        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.e(
                "API->",
                message
            )
        }
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    retrofit=Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

    fun getServices():WebServices{
   return  retrofit.create(WebServices::class.java)
    }





}