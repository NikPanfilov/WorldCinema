package com.nikpanfilov.compilation.domain.repository

interface CompilationRepository {

	suspend fun dislike(movieId: String)
}