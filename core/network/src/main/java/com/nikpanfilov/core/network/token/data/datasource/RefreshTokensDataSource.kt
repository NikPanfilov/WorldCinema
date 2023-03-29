package com.nikpanfilov.core.network.token.data.datasource

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair

interface RefreshTokensDataSource {

	suspend fun refresh(): AuthTokenPair
}