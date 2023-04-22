package com.nikpanfilov.shared.profile.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDataDto(
	@Json(name = "userId") val userId: String,
	@Json(name = "firstName") val firstName: String,
	@Json(name = "lastName") val lastName: String,
	@Json(name = "email") val email: String,
	@Json(name = "avatar") val avatar: String
)