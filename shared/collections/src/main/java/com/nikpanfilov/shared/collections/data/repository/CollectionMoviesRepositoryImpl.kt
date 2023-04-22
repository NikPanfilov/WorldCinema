package com.nikpanfilov.shared.collections.data.repository

import com.nikpanfilov.shared.collections.data.datasource.CollectionMoviesDataSource
import com.nikpanfilov.shared.collections.domain.entity.Movie
import com.nikpanfilov.shared.collections.domain.entity.MovieValue
import com.nikpanfilov.shared.collections.domain.repository.CollectionMoviesRepository

class CollectionMoviesRepositoryImpl(private val dataSource: CollectionMoviesDataSource) : CollectionMoviesRepository {

	override suspend fun getMovieCollection(id: String): List<Movie> = dataSource.getMovieCollection(id)

	override suspend fun addToCollection(id: String, movie: MovieValue) =
		dataSource.addToCollection(id, movie)

	override suspend fun deleteFromCollection(collectionId: String, movieId: String) =
		dataSource.deleteFromCollection(collectionId, movieId)
}