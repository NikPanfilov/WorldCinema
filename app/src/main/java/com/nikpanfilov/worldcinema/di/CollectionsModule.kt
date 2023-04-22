package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.collections.presentation.ChooseIconViewModel
import com.nikpanfilov.collections.presentation.CollectionMoviesViewModel
import com.nikpanfilov.collections.presentation.CollectionsViewModel
import com.nikpanfilov.collections.presentation.EditCollectionViewModel
import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.shared.collections.data.api.CollectionMoviesApi
import com.nikpanfilov.shared.collections.data.api.CollectionsApi
import com.nikpanfilov.shared.collections.data.datasource.CollectionMoviesDataSource
import com.nikpanfilov.shared.collections.data.datasource.CollectionMoviesDataSourceImpl
import com.nikpanfilov.shared.collections.data.datasource.CollectionsDataSource
import com.nikpanfilov.shared.collections.data.datasource.CollectionsDataSourceImpl
import com.nikpanfilov.shared.collections.data.repository.CollectionMoviesRepositoryImpl
import com.nikpanfilov.shared.collections.data.repository.CollectionsRepositoryImpl
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.repository.CollectionMoviesRepository
import com.nikpanfilov.shared.collections.domain.repository.CollectionsRepository
import com.nikpanfilov.shared.collections.domain.usecase.AddMovieToCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.CreateCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.CreateFavouriteCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.DeleteCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.DeleteMovieFromCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetCollectionsUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetFavouriteCollectionIdUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetMoviesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val collectionsModule = module {
	viewModel {
		CollectionsViewModel(
			router = get(),
			getCollectionsUseCase = get()
		)
	}

	viewModel { (collection: Collection) ->
		CollectionMoviesViewModel(
			collection = collection,
			router = get(),
			getMoviesUseCase = get()
		)
	}

	viewModel { (collection: Collection) ->
		EditCollectionViewModel(
			collection = collection,
			router = get(),
			getCollectionsUseCase = get(),
			createCollectionUseCase = get(),
			deleteCollectionUseCase = get(),
			getMoviesUseCase = get(),
			addMovieToCollectionUseCase = get()
		)
	}

	viewModel { (collection: Collection) ->
		ChooseIconViewModel(
			collection = collection,
			router = get()
		)
	}

	factory { createRetrofitService<CollectionsApi>(get(named(ORIGINAL))) }
	factory { createRetrofitService<CollectionMoviesApi>(get(named(ORIGINAL))) }

	factory<CollectionsDataSource> { CollectionsDataSourceImpl(get()) }
	factory<CollectionMoviesDataSource> { CollectionMoviesDataSourceImpl(get()) }

	single<CollectionsRepository> { CollectionsRepositoryImpl(get()) }
	single<CollectionMoviesRepository> { CollectionMoviesRepositoryImpl(get()) }

	single { GetCollectionsUseCase(get()) }
	single { CreateCollectionUseCase(get()) }
	single { DeleteCollectionUseCase(get()) }
	single { GetMoviesUseCase(get()) }
	single { AddMovieToCollectionUseCase(get()) }
	single { DeleteMovieFromCollectionUseCase(get()) }
	single { CreateFavouriteCollectionUseCase(get()) }
	single { GetFavouriteCollectionIdUseCase(get()) }
}