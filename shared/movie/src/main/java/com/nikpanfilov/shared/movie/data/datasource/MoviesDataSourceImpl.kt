package com.nikpanfilov.shared.movie.data.datasource

import com.nikpanfilov.shared.movie.data.api.MoviesApi
import com.nikpanfilov.shared.movie.domain.entity.Movie
import com.nikpanfilov.shared.movie.toEntity

class MoviesDataSourceImpl(private val api: MoviesApi) : MoviesDataSource {

	override suspend fun getMovies(filter: String): List<Movie> = api.getMovies(filter).map { it.toEntity() }
}