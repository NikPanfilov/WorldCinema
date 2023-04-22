package com.nikpanfilov.shared.movie.data.datasource

import com.nikpanfilov.shared.movie.domain.entity.Movie

interface MoviesDataSource {

	suspend fun getMovies(filter: String): List<Movie>
}