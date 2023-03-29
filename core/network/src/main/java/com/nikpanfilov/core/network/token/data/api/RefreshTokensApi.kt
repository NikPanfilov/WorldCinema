package com.nikpanfilov.core.network.token.data.api

import com.nikpanfilov.core.network.token.data.dto.AuthTokenPairDto
import retrofit2.http.POST

interface RefreshTokensApi {

	@POST("api/auth/refresh")
	suspend fun refresh(): AuthTokenPairDto
}