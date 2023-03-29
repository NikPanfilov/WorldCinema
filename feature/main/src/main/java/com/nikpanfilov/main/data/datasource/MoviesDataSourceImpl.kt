package com.nikpanfilov.main.data.datasource

import com.nikpanfilov.main.data.api.MoviesApi
import com.nikpanfilov.main.data.mapper.toEntity
import com.nikpanfilov.main.domain.entity.Movie

class MoviesDataSourceImpl(private val api: MoviesApi) : MoviesDataSource {

	override suspend fun getMovies(filter: String): List<Movie> = api.getMovies(filter).map { it.toEntity() }
}