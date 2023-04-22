package com.nikpanfilov.chats.list.domain.usecase

import com.nikpanfilov.chats.list.domain.entity.Chat
import com.nikpanfilov.chats.list.domain.repository.ChatsRepository

class GetChatsUseCase(private val repository: ChatsRepository) {

	suspend operator fun invoke(): List<Chat> = repository.getChats()
}