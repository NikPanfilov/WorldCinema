package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.domain.repository.CollectionMoviesRepository

class DeleteMovieFromCollectionUseCase(private val repository: CollectionMoviesRepository) {

	suspend operator fun invoke(collectionId: String, movieId: String) = repository.deleteFromCollection(collectionId, movieId)
}