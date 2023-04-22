package com.nikpanfilov.shared.collections.data.mapper

import com.nikpanfilov.shared.collections.data.dto.MovieValueDto
import com.nikpanfilov.shared.collections.domain.entity.MovieValue

internal fun MovieValue.toDto() = MovieValueDto(movieId = movieId)