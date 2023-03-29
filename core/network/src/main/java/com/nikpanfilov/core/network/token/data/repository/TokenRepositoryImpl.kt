package com.nikpanfilov.core.network.token.data.repository

import com.nikpanfilov.core.network.token.data.storage.TokenDataStorage
import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.core.network.token.domain.repository.TokenRepository

class TokenRepositoryImpl(
	private val storage: TokenDataStorage
) : TokenRepository {

	override fun save(authTokenPair: AuthTokenPair) = storage.save(authTokenPair)

	override fun load() = storage.load()
}