package com.example.giphyapi.data.models

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Data(
    val images: Images,
    val url: String,
    val title: String,
    val bitly_url: String,
    val type: String
) : Serializable