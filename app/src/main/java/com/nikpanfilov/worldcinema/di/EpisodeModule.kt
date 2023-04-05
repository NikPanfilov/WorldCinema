package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieInfoHolder
import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.episode.data.api.TimeApi
import com.nikpanfilov.episode.data.datasource.TimeDataSource
import com.nikpanfilov.episode.data.datasource.TimeDataSourceImpl
import com.nikpanfilov.episode.data.repository.TimeRepositoryImpl
import com.nikpanfilov.episode.domain.repository.TimeRepository
import com.nikpanfilov.episode.domain.usecase.GetTimeUseCase
import com.nikpanfilov.episode.domain.usecase.SetTimeUseCase
import com.nikpanfilov.episode.presentation.EpisodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val episodeModule = module {
	viewModel { (episode: EpisodeHolder, movieInfo: MovieInfoHolder) ->
		EpisodeViewModel(
			router = get(),
			getTimeUseCase = get(),
			setTimeUseCase = get(),
			episodeHolder = episode,
			movieInfoHolder = movieInfo
		)
	}

	factory { createRetrofitService<TimeApi>(get(named(ORIGINAL))) }

	factory<TimeDataSource> { TimeDataSourceImpl(get()) }

	single<TimeRepository> { TimeRepositoryImpl(get()) }

	single { GetTimeUseCase(get()) }
	single { SetTimeUseCase(get()) }
}