package com.nikpanfilov.signup.data.datasource

import com.nikpanfilov.core.network.token.data.mapper.toEntity
import com.nikpanfilov.signup.data.api.SignUpApi
import com.nikpanfilov.signup.data.mapper.toDto
import com.nikpanfilov.signup.domain.entity.RegistrationBody

class SignUpDataSourceImpl(private val api: SignUpApi) : SignUpDataSource {

	override suspend fun signUp(data: RegistrationBody) = api.signUp(data.toDto()).toEntity()
}