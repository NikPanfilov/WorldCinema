package com.nikpanfilov.signup.data.repository

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.signup.data.datasource.SignUpDataSource
import com.nikpanfilov.signup.domain.entity.RegistrationBody
import com.nikpanfilov.signup.domain.repository.SignUpRepository

class SignUpRepositoryImpl(private val dataSource: SignUpDataSource) : SignUpRepository {

	override suspend fun signUp(data: RegistrationBody) = dataSource.signUp(data)
}