package com.nikpanfilov.signin.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthCredentialDto(
	@Json(name = "email") val email: String,
	@Json(name = "password") val password: String
)
