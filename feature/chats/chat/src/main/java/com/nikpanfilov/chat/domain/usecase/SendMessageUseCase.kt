package com.nikpanfilov.chat.domain.usecase

import com.nikpanfilov.chat.domain.repository.ChatRepository

class SendMessageUseCase(private val repository: ChatRepository) {

	suspend operator fun invoke(text: String) = repository.send(text)
}