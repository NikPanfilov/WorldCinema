package com.nikpanfilov.signup.domain.usecase

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.signup.domain.entity.RegistrationBody
import com.nikpanfilov.signup.domain.repository.SignUpRepository

class SignUpUseCase(private val repository: SignUpRepository) {

	suspend operator fun invoke(data: RegistrationBody): AuthTokenPair = repository.signUp(data)
}