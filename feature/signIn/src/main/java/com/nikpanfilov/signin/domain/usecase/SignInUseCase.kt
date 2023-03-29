package com.nikpanfilov.signin.domain.usecase

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.signin.domain.entity.AuthCredential
import com.nikpanfilov.signin.domain.repository.SignInRepository

class SignInUseCase(private val repository: SignInRepository) {

	suspend operator fun invoke(credential: AuthCredential): AuthTokenPair = repository.signIn(credential)
}