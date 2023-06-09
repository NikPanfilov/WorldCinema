package com.nikpanfilov.core.network.token.domain.usecase

import com.nikpanfilov.core.network.token.domain.repository.RefreshTokensRepository

class RefreshTokensUseCase(private val repository: RefreshTokensRepository) {

	suspend operator fun invoke() = repository.refreshTokens()
}