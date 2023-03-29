package com.nikpanfilov.core.network.token.domain.usecase

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.core.network.token.domain.repository.TokenRepository

class SaveTokenUseCase(
	private val repository: TokenRepository,
) {

	operator fun invoke(authTokenPair: AuthTokenPair) = repository.save(authTokenPair)
}