package com.nikpanfilov.core.network.token.data.storage

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair

interface TokenDataStorage {

	fun save(authTokenPair: AuthTokenPair)

	fun load(): AuthTokenPair
}