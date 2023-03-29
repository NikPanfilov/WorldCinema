package com.nikpanfilov.signin.data.datasource

import com.nikpanfilov.core.network.token.data.mapper.toEntity
import com.nikpanfilov.signin.data.api.SignInApi
import com.nikpanfilov.signin.data.mapper.toDto
import com.nikpanfilov.signin.domain.entity.AuthCredential

class SignInDataSourceImpl(private val api: SignInApi) : SignInDataSource {

	override suspend fun signIn(credential: AuthCredential) = api.signIn(credential.toDto()).toEntity()
}