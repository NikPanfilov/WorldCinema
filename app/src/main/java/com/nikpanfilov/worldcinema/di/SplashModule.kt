package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.splash.data.repository.FirstStartRepositoryImpl
import com.nikpanfilov.splash.data.storage.FirstStartDataStorage
import com.nikpanfilov.splash.data.storage.FirstStartDataStorageImpl
import com.nikpanfilov.splash.domain.repository.FirstStartRepository
import com.nikpanfilov.splash.domain.usecase.CheckFirstStartUseCase
import com.nikpanfilov.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
	viewModel {
		SplashViewModel(
			router = get(),
			saveTokenUseCase = get(),
			loadTokenUseCase = get(),
			refreshTokensUseCase = get(),
			checkFirstStartUseCase = get()
		)
	}

	single<FirstStartDataStorage> { FirstStartDataStorageImpl(get()) }
	single<FirstStartRepository> { FirstStartRepositoryImpl(get()) }
	single { CheckFirstStartUseCase(get()) }
}
