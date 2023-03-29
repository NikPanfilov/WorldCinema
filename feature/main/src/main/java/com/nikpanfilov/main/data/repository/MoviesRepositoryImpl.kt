package com.nikpanfilov.main.data.repository

import com.nikpanfilov.main.data.datasource.MoviesDataSource
import com.nikpanfilov.main.domain.entity.Movie
import com.nikpanfilov.main.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val dataSource: MoviesDataSource) : MoviesRepository {

	override suspend fun getMovies(filter: String): List<Movie> = dataSource.getMovies(filter)
}