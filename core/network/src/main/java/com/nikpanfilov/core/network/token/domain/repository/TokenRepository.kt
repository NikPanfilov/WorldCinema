package com.nikpanfilov.core.network.token.domain.repository

import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair

interface TokenRepository {

	fun save(authTokenPair: AuthTokenPair)

	fun load(): AuthTokenPair
}