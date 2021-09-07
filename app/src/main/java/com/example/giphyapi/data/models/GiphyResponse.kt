package com.example.giphyapi.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyResponse(
    val `data`: List<Data>
)