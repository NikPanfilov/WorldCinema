package com.nikpanfilov.signup.domain.repository

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.signup.domain.entity.RegistrationBody

interface SignUpRepository {

	suspend fun signUp(data: RegistrationBody): AuthTokenPair
}