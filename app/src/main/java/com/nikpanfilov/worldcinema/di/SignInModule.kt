package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.signin.data.api.SignInApi
import com.nikpanfilov.signin.data.datasource.SignInDataSource
import com.nikpanfilov.signin.data.datasource.SignInDataSourceImpl
import com.nikpanfilov.signin.data.repository.SignInRepositoryImpl
import com.nikpanfilov.signin.domain.repository.SignInRepository
import com.nikpanfilov.signin.domain.usecase.SignInUseCase
import com.nikpanfilov.signin.presentation.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signInModule = module {
	viewModel {
		SignInViewModel(
			router = get(),
			signInUseCase = get(),
			validateEmailUseCase = get(),
			saveTokenUseCase = get(),
			saveUserIdUseCase = get()
		)
	}

	factory { createRetrofitService<SignInApi>(get(named(ORIGINAL))) }

	factory<SignInDataSource> { SignInDataSourceImpl(get()) }

	single<SignInRepository> { SignInRepositoryImpl(get()) }

	single { SignInUseCase(get()) }
}
