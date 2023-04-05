package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.core.navigation.holders.MovieHolder
import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.movie.data.api.EpisodesApi
import com.nikpanfilov.movie.data.datasource.EpisodesDataSource
import com.nikpanfilov.movie.data.datasource.EpisodesDataSourceImpl
import com.nikpanfilov.movie.data.repository.EpisodesRepositoryImpl
import com.nikpanfilov.movie.domain.repository.EpisodesRepository
import com.nikpanfilov.movie.domain.usecase.GetEpisodesUseCase
import com.nikpanfilov.movie.presentation.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {
	viewModel { (movie: MovieHolder) ->
		MovieViewModel(
			router = get(),
			getEpisodesUseCase = get(),
			movieHolder = movie
		)
	}

	factory { createRetrofitService<EpisodesApi>(get(named(ORIGINAL))) }

	factory<EpisodesDataSource> { EpisodesDataSourceImpl(get()) }

	single<EpisodesRepository> { EpisodesRepositoryImpl(get()) }

	single { GetEpisodesUseCase(get()) }
}