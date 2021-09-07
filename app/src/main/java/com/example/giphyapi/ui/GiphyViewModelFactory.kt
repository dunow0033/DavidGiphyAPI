package com.example.giphyapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphyapi.Repository.GiphyRepository

class GiphyViewModelFactory(val giphyRepository: GiphyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GiphyViewModel(giphyRepository) as T
    }
}