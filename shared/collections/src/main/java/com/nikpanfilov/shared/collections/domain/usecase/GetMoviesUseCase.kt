package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.domain.repository.CollectionMoviesRepository

class GetMoviesUseCase(private val repository: CollectionMoviesRepository) {

	suspend operator fun invoke(id: String) = repository.getMovieCollection(id)
}