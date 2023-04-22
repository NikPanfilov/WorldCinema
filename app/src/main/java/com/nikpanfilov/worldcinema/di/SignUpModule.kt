package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.signup.data.api.SignUpApi
import com.nikpanfilov.signup.data.datasource.SignUpDataSource
import com.nikpanfilov.signup.data.datasource.SignUpDataSourceImpl
import com.nikpanfilov.signup.data.repository.SignUpRepositoryImpl
import com.nikpanfilov.signup.domain.repository.SignUpRepository
import com.nikpanfilov.signup.domain.usecase.SignUpUseCase
import com.nikpanfilov.signup.presentation.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signUpModule = module {
	viewModel {
		SignUpViewModel(
			router = get(),
			signUpUseCase = get(),
			saveTokenUseCase = get(),
			validateFirstNameUseCase = get(),
			validateLastNameUseCase = get(),
			validateEmailUseCase = get(),
			validatePasswordUseCase = get(),
			createFavouriteCollectionUseCase = get(),
			saveUserIdUseCase = get()
		)
	}

	factory { createRetrofitService<SignUpApi>(get(named(ORIGINAL))) }

	factory<SignUpDataSource> { SignUpDataSourceImpl(get()) }

	single<SignUpRepository> { SignUpRepositoryImpl(get()) }

	single { SignUpUseCase(get()) }
}