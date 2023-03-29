package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.main.data.api.CoverApi
import com.nikpanfilov.main.data.api.MoviesApi
import com.nikpanfilov.main.data.datasource.CoverDataSource
import com.nikpanfilov.main.data.datasource.CoverDataSourceImpl
import com.nikpanfilov.main.data.datasource.MoviesDataSource
import com.nikpanfilov.main.data.datasource.MoviesDataSourceImpl
import com.nikpanfilov.main.data.repository.CoverRepositoryImpl
import com.nikpanfilov.main.data.repository.MoviesRepositoryImpl
import com.nikpanfilov.main.domain.repository.CoverRepository
import com.nikpanfilov.main.domain.repository.MoviesRepository
import com.nikpanfilov.main.domain.usecase.GetCoverUseCase
import com.nikpanfilov.main.domain.usecase.GetMoviesUseCase
import com.nikpanfilov.main.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
	viewModel {
		MainViewModel(
			router = get(),
			getMoviesUseCase = get(),
			getCoverUseCase = get()
		)
	}

	factory { createRetrofitService<MoviesApi>(get(named(ORIGINAL))) }
	factory { createRetrofitService<CoverApi>(get(named(ORIGINAL))) }

	factory<MoviesDataSource> { MoviesDataSourceImpl(get()) }
	factory<CoverDataSource> { CoverDataSourceImpl(get()) }

	single<MoviesRepository> { MoviesRepositoryImpl(get()) }
	single<CoverRepository> { CoverRepositoryImpl(get()) }

	single { GetMoviesUseCase(get()) }
	single { GetCoverUseCase(get()) }
}