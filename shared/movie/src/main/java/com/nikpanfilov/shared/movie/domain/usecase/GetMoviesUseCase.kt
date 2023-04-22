package com.nikpanfilov.shared.movie.domain.usecase

import com.nikpanfilov.shared.movie.domain.entity.Movie
import com.nikpanfilov.shared.movie.domain.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {

	suspend operator fun invoke(filter: String): List<Movie> = repository.getMovies(filter)
}