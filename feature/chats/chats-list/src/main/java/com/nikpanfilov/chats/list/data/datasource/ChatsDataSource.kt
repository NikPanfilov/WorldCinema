package com.nikpanfilov.chats.list.data.datasource

import com.nikpanfilov.chats.list.domain.entity.Chat

interface ChatsDataSource {

	suspend fun getChats(): List<Chat>
}