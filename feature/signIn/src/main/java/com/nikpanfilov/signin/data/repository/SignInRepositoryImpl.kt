package com.nikpanfilov.signin.data.repository

import com.nikpanfilov.signin.data.datasource.SignInDataSource
import com.nikpanfilov.signin.domain.entity.AuthCredential
import com.nikpanfilov.signin.domain.repository.SignInRepository

class SignInRepositoryImpl(
	private val dataSource: SignInDataSource
) : SignInRepository {

	override suspend fun signIn(credential: AuthCredential) = dataSource.signIn(credential)
}