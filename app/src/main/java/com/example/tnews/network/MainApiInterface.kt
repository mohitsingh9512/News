package com.example.tnews.network

import com.example.tnews.network.response.NewsResponse
import com.example.tnews.network.response.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiInterface {

    @GET("sources")
    fun getSources(): Call<SourcesResponse>

    @GET("everything")
    fun getNews(@Query("sources") source : String): Call<NewsResponse>
}

