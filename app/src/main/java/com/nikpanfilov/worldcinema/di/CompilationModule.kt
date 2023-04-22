package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.compilation.data.api.CompilationApi
import com.nikpanfilov.compilation.data.datasource.CompilationDataSource
import com.nikpanfilov.compilation.data.datasource.CompilationDataSourceImpl
import com.nikpanfilov.compilation.data.repository.CompilationRepositoryImpl
import com.nikpanfilov.compilation.domain.repository.CompilationRepository
import com.nikpanfilov.compilation.domain.usecase.DislikeMovieUseCase
import com.nikpanfilov.compilation.presentation.CompilationViewModel
import com.nikpanfilov.core.network.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val compilationModule = module {
	viewModel {
		CompilationViewModel(
			router = get(),
			getMoviesUseCase = get(),
			dislikeMovieUseCase = get(),
			addMovieToCollectionUseCase = get(),
			getFavouriteCollectionIdUseCase = get()
		)
	}

	factory { createRetrofitService<CompilationApi>(get(named(ORIGINAL))) }

	factory<CompilationDataSource> { CompilationDataSourceImpl(get()) }

	single<CompilationRepository> { CompilationRepositoryImpl(get()) }

	single { DislikeMovieUseCase(get()) }
}