package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.chat.data.datasource.ChatDataSource
import com.nikpanfilov.chat.data.datasource.ChatDataSourceImpl
import com.nikpanfilov.chat.data.repository.ChatRepositoryImpl
import com.nikpanfilov.chat.domain.repository.ChatRepository
import com.nikpanfilov.chat.domain.usecase.ConnectToChatUseCase
import com.nikpanfilov.chat.domain.usecase.DisconnectUseCase
import com.nikpanfilov.chat.domain.usecase.GetMessagesUseCase
import com.nikpanfilov.chat.domain.usecase.SendMessageUseCase
import com.nikpanfilov.chat.presentation.ChatViewModel
import com.nikpanfilov.chats.list.data.api.ChatsListApi
import com.nikpanfilov.chats.list.data.datasource.ChatsDataSource
import com.nikpanfilov.chats.list.data.datasource.ChatsDataSourceImpl
import com.nikpanfilov.chats.list.data.repository.ChatsRepositoryImpl
import com.nikpanfilov.chats.list.domain.repository.ChatsRepository
import com.nikpanfilov.chats.list.domain.usecase.GetChatsUseCase
import com.nikpanfilov.chats.list.presentation.ChatsListViewModel
import com.nikpanfilov.core.network.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val chatsModule = module {
	viewModel {
		ChatsListViewModel(
			router = get(),
			getChatsUseCase = get(),
			getUserIdUseCase = get()
		)
	}

	factory { createRetrofitService<ChatsListApi>(get(named(ORIGINAL))) }

	factory<ChatsDataSource> { ChatsDataSourceImpl(get()) }

	single<ChatsRepository> { ChatsRepositoryImpl(get()) }

	single { GetChatsUseCase(get()) }

	viewModel { (chatId: String) ->
		ChatViewModel(
			chatId = chatId,
			router = get(),
			connectToChatUseCase = get(),
			disconnectUseCase = get(),
			getMessagesUseCase = get(),
			sendMessageUseCase = get(),
			getUserIdUseCase = get()
		)
	}

	single<ChatDataSource> { ChatDataSourceImpl(client = get(named(ORIGINAL)), moshi = get()) }
	single<ChatRepository> { ChatRepositoryImpl(get()) }

	single { ConnectToChatUseCase(get()) }
	single { DisconnectUseCase(get()) }
	single { GetMessagesUseCase(get()) }
	single { SendMessageUseCase(get()) }
}