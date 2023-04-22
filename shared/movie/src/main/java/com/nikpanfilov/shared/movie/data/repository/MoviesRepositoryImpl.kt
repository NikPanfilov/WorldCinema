package com.nikpanfilov.shared.movie.data.repository

import com.nikpanfilov.shared.movie.data.datasource.MoviesDataSource
import com.nikpanfilov.shared.movie.domain.entity.Movie
import com.nikpanfilov.shared.movie.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val dataSource: MoviesDataSource) : MoviesRepository {

	override suspend fun getMovies(filter: String): List<Movie> = dataSource.getMovies(filter)
}