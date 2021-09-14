package com.example.giphyapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyapi.Repository.GiphyRepository
import com.example.giphyapi.data.models.GiphyResponse
import com.example.giphyapi.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class GiphyViewModel(val repo: GiphyRepository) : ViewModel() {


    private var _giphyData: MutableLiveData<Resource<GiphyResponse>> = MutableLiveData()
    val giphyData: LiveData<Resource<GiphyResponse>> get() = _giphyData

    private var _searchNewsData: MutableLiveData<Resource<GiphyResponse>> = MutableLiveData()
    val searchNewsData: LiveData<Resource<GiphyResponse>> get() = _searchNewsData

    init {
        getGiphy()
    }


    fun getGiphy() {

        viewModelScope.launch {
            repo.getGiphy().collect {
                _giphyData.postValue(it)
            }

        }
    }

    fun searchGiphy(searchQuery: String, limit: String, APIKEY: String) =
        viewModelScope.launch {
            _searchNewsData.postValue(Resource.Loading())
            val response = repo.searchGiphy(searchQuery, limit, APIKEY)
            _searchNewsData.postValue((response))
        }

}