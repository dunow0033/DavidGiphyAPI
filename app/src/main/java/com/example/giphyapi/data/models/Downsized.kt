package com.example.giphyapi.data.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Downsized(
    val height: String,
    val size: String,
    val url: String,
    val width: String
)