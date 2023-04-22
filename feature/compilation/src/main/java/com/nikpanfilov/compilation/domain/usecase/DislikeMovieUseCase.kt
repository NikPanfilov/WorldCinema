package com.nikpanfilov.compilation.domain.usecase

import com.nikpanfilov.compilation.domain.repository.CompilationRepository

class DislikeMovieUseCase(private val repository: CompilationRepository) {

	suspend operator fun invoke(movieId: String) {
		repository.dislike(movieId)
	}
}