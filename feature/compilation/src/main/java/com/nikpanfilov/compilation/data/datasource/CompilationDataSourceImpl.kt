package com.nikpanfilov.compilation.data.datasource

import com.nikpanfilov.compilation.data.api.CompilationApi

class CompilationDataSourceImpl(private val api: CompilationApi) : CompilationDataSource {

	override suspend fun dislike(movieId: String) {
		api.dislike(movieId)
	}
}