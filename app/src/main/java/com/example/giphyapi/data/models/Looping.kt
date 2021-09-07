package com.example.giphyapi.data.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Looping(
    val mp4: String,
    val mp4_size: String
)