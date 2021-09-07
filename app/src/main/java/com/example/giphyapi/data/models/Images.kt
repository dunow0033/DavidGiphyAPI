package com.example.giphyapi.data.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Images(
    //val downsized: Downsized,
    //val downsized_still: DownsizedStill,
    //val hd: Hd,
    // val looping: Looping,
    val original: Original,
    val original_still: OriginalStill,
    // val preview: Preview,
    //val preview_gif: PreviewGif
)