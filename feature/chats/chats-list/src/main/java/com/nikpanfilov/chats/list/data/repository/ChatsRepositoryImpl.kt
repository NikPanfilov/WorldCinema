package com.nikpanfilov.chats.list.data.repository

import com.nikpanfilov.chats.list.data.datasource.ChatsDataSource
import com.nikpanfilov.chats.list.domain.entity.Chat
import com.nikpanfilov.chats.list.domain.repository.ChatsRepository

class ChatsRepositoryImpl(private val dataSource: ChatsDataSource) : ChatsRepository {

	override suspend fun getChats(): List<Chat> = dataSource.getChats()
}