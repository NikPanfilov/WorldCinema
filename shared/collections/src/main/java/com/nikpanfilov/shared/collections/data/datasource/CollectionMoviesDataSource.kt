package com.nikpanfilov.shared.collections.data.datasource

import com.nikpanfilov.shared.collections.domain.entity.Movie
import com.nikpanfilov.shared.collections.domain.entity.MovieValue

interface CollectionMoviesDataSource {

	suspend fun getMovieCollection(id: String): List<Movie>

	suspend fun addToCollection(id: String, movie: MovieValue)

	suspend fun deleteFromCollection(collectionId: String, movieId: String)
}