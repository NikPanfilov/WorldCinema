package com.nikpanfilov.shared.collections.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieValueDto(@Json(name = "movieId") val movieId: String)
