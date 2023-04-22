package com.nikpanfilov.shared.collections.data.datasource

import com.nikpanfilov.shared.collections.data.api.CollectionMoviesApi
import com.nikpanfilov.shared.collections.data.mapper.toDto
import com.nikpanfilov.shared.collections.data.mapper.toEntity
import com.nikpanfilov.shared.collections.domain.entity.Movie
import com.nikpanfilov.shared.collections.domain.entity.MovieValue

class CollectionMoviesDataSourceImpl(private val api: CollectionMoviesApi) : CollectionMoviesDataSource {

	override suspend fun getMovieCollection(id: String): List<Movie> = api.getMovieCollection(id).map { it.toEntity() }

	override suspend fun addToCollection(id: String, movie: MovieValue) = api.addToCollection(id, movie.toDto())

	override suspend fun deleteFromCollection(collectionId: String, movieId: String) = api.deleteFromCollection(collectionId, movieId)
}