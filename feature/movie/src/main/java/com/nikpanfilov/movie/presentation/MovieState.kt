package com.nikpanfilov.movie.presentation

sealed class MovieState {

	object Initial : MovieState()

	data class Content(val sendState: MovieSendState) : MovieState()
}

sealed class MovieSendState {

	object Input : MovieSendState()

	object Loading : MovieSendState()

	object Success : MovieSendState()

	data class Error(val errorCode: Int) : MovieSendState()
}