package com.nikpanfilov.shared.movie.domain.repository

import com.nikpanfilov.shared.movie.domain.entity.Movie

interface MoviesRepository {

	suspend fun getMovies(filter: String): List<Movie>
}