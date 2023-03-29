package com.nikpanfilov.core.network.token.domain.repository

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair

interface RefreshTokensRepository {

	suspend fun refreshTokens(): AuthTokenPair
}