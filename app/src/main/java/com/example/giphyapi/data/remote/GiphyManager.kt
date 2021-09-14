package com.example.giphyapi.data.remote

import com.example.giphyapi.utils.Constants.Companion.API_KEY

class GiphyManager {

    private val giphyService: GiphyService
    private val retrofit = RetrofitInstance.providesRetrofit()


    init {
        giphyService = retrofit.create(GiphyService::class.java)
    }


    suspend fun getGiphy() = giphyService.getGiphy("40", API_KEY)

    suspend fun searchGiphy(search: String , limit: String, APIKEY: String) =
         giphyService.searchGiphy(search,limit,APIKEY)


}







