package com.nikpanfilov.shared.collections.domain.repository

import com.nikpanfilov.shared.collections.domain.entity.Movie
import com.nikpanfilov.shared.collections.domain.entity.MovieValue

interface CollectionMoviesRepository {

	suspend fun getMovieCollection(id: String): List<Movie>

	suspend fun addToCollection(id: String, movie: MovieValue)

	suspend fun deleteFromCollection(collectionId: String, movieId: String)
}