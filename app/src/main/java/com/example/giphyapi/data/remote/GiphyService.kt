package com.example.giphyapi.data.remote

import com.example.giphyapi.data.models.GiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("v1/gifs/trending")
    suspend fun getGiphy(
        @Query("limit") limit: String,
        @Query("api_key") APIKEY: String

    ): Response<GiphyResponse>



    @GET("v1/gifs/search")
    suspend fun searchGiphy(
        @Query("q") search: String,
        @Query("limit") limit: String,
        @Query("api_key") APIKEY: String
    ): Response<GiphyResponse>


}