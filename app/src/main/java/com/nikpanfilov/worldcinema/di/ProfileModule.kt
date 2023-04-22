package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.core.network.createRetrofitService
import com.nikpanfilov.profile.data.api.AvatarApi
import com.nikpanfilov.profile.data.datasource.AvatarDataSource
import com.nikpanfilov.profile.data.datasource.AvatarDataSourceImpl
import com.nikpanfilov.profile.data.repository.AvatarRepositoryImpl
import com.nikpanfilov.profile.data.repository.LogOutRepositoryImpl
import com.nikpanfilov.profile.data.storage.LogOutDataStorage
import com.nikpanfilov.profile.data.storage.LogOutDataStorageImpl
import com.nikpanfilov.profile.domain.repository.AvatarRepository
import com.nikpanfilov.profile.domain.repository.LogOutRepository
import com.nikpanfilov.profile.domain.usecase.LogOutUseCase
import com.nikpanfilov.profile.domain.usecase.SetAvatarUseCase
import com.nikpanfilov.profile.presentation.ProfileViewModel
import com.nikpanfilov.shared.profile.data.api.ProfileApi
import com.nikpanfilov.shared.profile.data.datasource.ProfileDataSource
import com.nikpanfilov.shared.profile.data.datasource.ProfileDataSourceImpl
import com.nikpanfilov.shared.profile.data.repository.ProfileRepositoryImpl
import com.nikpanfilov.shared.profile.data.repository.UserIdRepositoryImpl
import com.nikpanfilov.shared.profile.data.storage.UserIdDataStorage
import com.nikpanfilov.shared.profile.data.storage.UserIdDataStorageImpl
import com.nikpanfilov.shared.profile.domain.repository.ProfileRepository
import com.nikpanfilov.shared.profile.domain.repository.UserIdRepository
import com.nikpanfilov.shared.profile.domain.usecase.GetProfileUseCase
import com.nikpanfilov.shared.profile.domain.usecase.GetUserIdUseCase
import com.nikpanfilov.shared.profile.domain.usecase.SaveUserIdUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val profileModule = module {
	viewModel {
		ProfileViewModel(
			router = get(),
			getProfileUseCase = get(),
			setAvatarUseCase = get(),
			logOutUseCase = get()
		)
	}

	factory { createRetrofitService<ProfileApi>(get(named(ORIGINAL))) }
	factory { createRetrofitService<AvatarApi>(get(named(ORIGINAL))) }

	factory<AvatarDataSource> { AvatarDataSourceImpl(get()) }
	factory<ProfileDataSource> { ProfileDataSourceImpl(get()) }

	factory<UserIdDataStorage> { UserIdDataStorageImpl(get()) }

	single<LogOutDataStorage> { LogOutDataStorageImpl(get()) }

	single<AvatarRepository> { AvatarRepositoryImpl(get()) }
	single<LogOutRepository> { LogOutRepositoryImpl(get()) }

	single<ProfileRepository> { ProfileRepositoryImpl(get()) }
	single<UserIdRepository> { UserIdRepositoryImpl(get()) }

	single { GetProfileUseCase(get()) }
	single { GetUserIdUseCase(get()) }
	single { SaveUserIdUseCase(get(), get()) }

	single { SetAvatarUseCase(get()) }
	single { LogOutUseCase(get()) }
}