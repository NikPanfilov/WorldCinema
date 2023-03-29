package com.nikpanfilov.signup.data.datasource

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.signup.domain.entity.RegistrationBody

interface SignUpDataSource {

	suspend fun signUp(data: RegistrationBody): AuthTokenPair
}