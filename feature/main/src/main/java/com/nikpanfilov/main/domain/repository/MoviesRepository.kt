package com.nikpanfilov.main.domain.repository

import com.nikpanfilov.main.domain.entity.Movie

interface MoviesRepository {

	suspend fun getMovies(filter: String): List<Movie>
}