package com.nikpanfilov.core.network.token.data.datasource

import com.nikpanfilov.core.network.token.data.api.RefreshTokensApi
import com.nikpanfilov.core.network.token.data.mapper.toEntity

class RefreshTokensDataSourceImpl(private val api: RefreshTokensApi) : RefreshTokensDataSource {

	override suspend fun refresh() = api.refresh().toEntity()
}