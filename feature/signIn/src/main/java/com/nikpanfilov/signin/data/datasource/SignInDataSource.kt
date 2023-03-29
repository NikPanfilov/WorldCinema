package com.nikpanfilov.signin.data.datasource

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.signin.domain.entity.AuthCredential

interface SignInDataSource {

	suspend fun signIn(credential: AuthCredential): AuthTokenPair
}