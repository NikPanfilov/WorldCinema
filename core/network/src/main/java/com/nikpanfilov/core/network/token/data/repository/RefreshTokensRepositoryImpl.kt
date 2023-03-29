package com.nikpanfilov.core.network.token.data.repository

import com.nikpanfilov.core.network.token.data.datasource.RefreshTokensDataSource
import com.nikpanfilov.core.network.token.domain.repository.RefreshTokensRepository

class RefreshTokensRepositoryImpl(
	private val dataSource: RefreshTokensDataSource
) : RefreshTokensRepository {

	override suspend fun refreshTokens() = dataSource.refresh()
}