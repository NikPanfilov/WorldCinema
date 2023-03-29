package com.nikpanfilov.core.network.token.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthTokenPairDto(
	@Json(name = "accessToken") val accessToken: String,
	@Json(name = "refreshToken") val refreshToken: String
)