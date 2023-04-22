package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.domain.entity.MovieValue
import com.nikpanfilov.shared.collections.domain.repository.CollectionMoviesRepository

class AddMovieToCollectionUseCase(private val repository: CollectionMoviesRepository) {

	suspend operator fun invoke(id: String, movie: MovieValue) = repository.addToCollection(id, movie)
}