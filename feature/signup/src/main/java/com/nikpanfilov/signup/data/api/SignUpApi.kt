package com.nikpanfilov.signup.data.api

import com.nikpanfilov.core.network.token.data.dto.AuthTokenPairDto
import com.nikpanfilov.signup.data.dto.RegistrationBodyDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

	@POST("api/auth/register")
	suspend fun signUp(@Body data: RegistrationBodyDto): AuthTokenPairDto
}