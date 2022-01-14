package com.lauwba.newsmvvm.repository.service

import android.provider.SyncStateContract
import com.lauwba.newsmvvm.model.NewsResponse
import com.lauwba.newsmvvm.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country :String = "us",  //You can add your county like us, etc
        @Query("page") pageNumber : Int,
        @Query("apiKey") apiKey : String = Constants.API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q") searchQuery :String,  //You can add your county like us, etc
        @Query("page") pageNumber : Int,
        @Query("apiKey") apiKey : String = Constants.API_KEY
    ): Response<NewsResponse>
}