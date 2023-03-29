package com.nikpanfilov.signin.domain.repository

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.signin.domain.entity.AuthCredential

interface SignInRepository {

	suspend fun signIn(credential: AuthCredential): AuthTokenPair
}