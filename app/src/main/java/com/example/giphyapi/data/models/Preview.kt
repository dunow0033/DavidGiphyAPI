package com.example.giphyapi.data.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Preview(
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val width: String
)