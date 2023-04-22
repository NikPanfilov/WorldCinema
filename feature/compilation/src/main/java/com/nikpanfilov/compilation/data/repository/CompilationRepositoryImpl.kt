package com.nikpanfilov.compilation.data.repository

import com.nikpanfilov.compilation.data.datasource.CompilationDataSource

class CompilationRepositoryImpl(private val dataSource: CompilationDataSource) : com.nikpanfilov.compilation.domain.repository.CompilationRepository {

	override suspend fun dislike(movieId: String) {
		dataSource.dislike(movieId)
	}
}