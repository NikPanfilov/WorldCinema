package com.nikpanfilov.shared.collections.data.mapper

import com.nikpanfilov.shared.collections.data.dto.MovieDto
import com.nikpanfilov.shared.collections.domain.entity.Movie

internal fun MovieDto.toEntity() = Movie(movieId = movieId, name = name, description = description, poster = poster)