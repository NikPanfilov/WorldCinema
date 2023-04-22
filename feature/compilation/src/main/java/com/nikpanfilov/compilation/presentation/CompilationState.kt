package com.nikpanfilov.compilation.presentation

sealed class CompilationState {

	object Initial : CompilationState()

	data class Content(val sendState: CompilationSendState) : CompilationState()
}

sealed class CompilationSendState {

	object Input : CompilationSendState()

	object Loading : CompilationSendState()

	object Success : CompilationSendState()

	data class Error(val errorCode: Int) : CompilationSendState()
}