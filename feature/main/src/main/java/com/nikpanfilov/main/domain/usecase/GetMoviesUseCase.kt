package com.nikpanfilov.main.domain.usecase

import com.nikpanfilov.main.domain.entity.Movie
import com.nikpanfilov.main.domain.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {

	suspend operator fun invoke(filter: String): List<Movie> = repository.getMovies(filter)
}