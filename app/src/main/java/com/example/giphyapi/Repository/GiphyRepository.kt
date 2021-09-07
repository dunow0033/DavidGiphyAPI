package com.example.giphyapi.Repository

import android.util.Log
import com.example.giphyapi.data.models.GiphyResponse
import com.example.giphyapi.data.remote.GiphyManager
import com.example.giphyapi.utils.Resource
import kotlinx.coroutines.flow.flow

class GiphyRepository(private val giphyManager: GiphyManager) {


    fun getGiphy() = flow {

        val resource =
            try {
                val response = giphyManager.getGiphy()
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(response.errorBody().toString())
                }

            } catch (ex: Exception) {
                Log.d(TAG, ex.toString())
                Resource.Error(ex.toString())
            }

        emit(resource)

    }

    suspend fun searchGiphy(search: String, limit: String, APIKEY: String) : Resource<GiphyResponse> {
      val response =  giphyManager.searchGiphy(search, limit, APIKEY)
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    companion object {
        private val TAG = GiphyRepository::class.java.name
    }

}