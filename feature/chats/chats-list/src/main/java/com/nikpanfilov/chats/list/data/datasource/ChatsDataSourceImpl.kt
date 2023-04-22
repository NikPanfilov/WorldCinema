package com.nikpanfilov.chats.list.data.datasource

import com.nikpanfilov.chats.list.data.api.ChatsListApi
import com.nikpanfilov.chats.list.data.mapper.toEntity
import com.nikpanfilov.chats.list.domain.entity.Chat

class ChatsDataSourceImpl(private val api: ChatsListApi) : ChatsDataSource {

	override suspend fun getChats(): List<Chat> = api.getChats().map { it.toEntity() }
}