package com.nikpanfilov.core.network.token.data.mapper

import com.nikpanfilov.core.network.token.data.dto.AuthTokenPairDto
import com.nikpanfilov.core.network.token.domain.model.AuthTokenPair

fun AuthTokenPairDto.toEntity() = AuthTokenPair(accessToken = accessToken, refreshToken = refreshToken)