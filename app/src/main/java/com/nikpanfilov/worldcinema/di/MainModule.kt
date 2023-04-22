package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.main.data.api.CoverApi
import com.nikpanfilov.main.data.api.HistoryApi
import com.nikpanfilov.main.data.datasource.CoverDataSource
import com.nikpanfilov.main.data.datasource.CoverDataSourceImpl
import com.nikpanfilov.main.data.datasource.HistoryDataSource
import com.nikpanfilov.main.data.datasource.HistoryDataSourceImpl
import com.nikpanfilov.main.data.repository.CoverRepositoryImpl
import com.nikpanfilov.main.data.repository.HistoryRepositoryImpl
import com.nikpanfilov.main.domain.repository.CoverRepository
import com.nikpanfilov.main.domain.repository.HistoryRepository
import com.nikpanfilov.main.domain.usecase.GetCoverUseCase
import com.nikpanfilov.main.domain.usecase.GetHistoryUseCase
import com.nikpanfilov.main.presentation.MainViewModel
import com.nikpanfilov.shared.movie.data.api.MoviesApi
import com.nikpanfilov.shared.movie.data.datasource.MoviesDataSource
import com.nikpanfilov.shared.movie.data.datasource.MoviesDataSourceImpl
import com.nikpanfilov.shared.movie.data.repository.MoviesRepositoryImpl
import com.nikpanfilov.shared.movie.domain.repository.MoviesRepository
import com.nikpanfilov.shared.movie.domain.usecase.GetMoviesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
	viewModel {
		MainViewModel(
			router = get(),
			getMoviesUseCase = get(),
			getCoverUseCase = get(),
			getHistoryUseCase = get()
		)
	}

	factory { createRetrofitService<MoviesApi>(get(named(ORIGINAL))) }
	factory { createRetrofitService<CoverApi>(get(named(ORIGINAL))) }
	factory { createRetrofitService<HistoryApi>(get(named(ORIGINAL))) }

	factory<MoviesDataSource> { MoviesDataSourceImpl(get()) }
	factory<CoverDataSource> { CoverDataSourceImpl(get()) }
	factory<HistoryDataSource> { HistoryDataSourceImpl(get()) }

	single<MoviesRepository> { MoviesRepositoryImpl(get()) }
	single<CoverRepository> { CoverRepositoryImpl(get()) }
	single<HistoryRepository> { HistoryRepositoryImpl(get()) }

	single { GetMoviesUseCase(get()) }
	single { GetCoverUseCase(get()) }
	single { GetHistoryUseCase(get()) }
}