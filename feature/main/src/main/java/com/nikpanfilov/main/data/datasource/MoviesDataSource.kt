package com.nikpanfilov.main.data.datasource

import com.nikpanfilov.main.domain.entity.Movie

interface MoviesDataSource {

	suspend fun getMovies(filter: String): List<Movie>
}