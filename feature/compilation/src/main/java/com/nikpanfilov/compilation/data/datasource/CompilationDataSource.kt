package com.nikpanfilov.compilation.data.datasource

interface CompilationDataSource {

	suspend fun dislike(movieId: String)
}