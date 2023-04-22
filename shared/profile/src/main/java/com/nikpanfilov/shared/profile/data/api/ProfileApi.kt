package com.nikpanfilov.shared.profile.data.api

import com.nikpanfilov.shared.profile.data.dto.UserDataDto
import retrofit2.http.GET

interface ProfileApi {

	@GET("api/profile")
	suspend fun getProfile(): UserDataDto
}