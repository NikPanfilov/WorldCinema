package com.nikpanfilov.signin.data.api

import com.nikpanfilov.core.network.token.data.dto.AuthTokenPairDto
import com.nikpanfilov.signin.data.dto.AuthCredentialDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {

	@POST("api/auth/login")
	suspend fun signIn(@Body data: AuthCredentialDto): AuthTokenPairDto
}