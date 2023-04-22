package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.chat.presentation.ChatRouter
import com.nikpanfilov.chats.list.presentation.ChatsListRouter
import com.nikpanfilov.collections.presentation.ChooseIconRouter
import com.nikpanfilov.collections.presentation.CollectionMoviesRouter
import com.nikpanfilov.collections.presentation.CollectionsRouter
import com.nikpanfilov.collections.presentation.EditCollectionRouter
import com.nikpanfilov.compilation.presentation.CompilationRouter
import com.nikpanfilov.episode.presentation.EpisodeRouter
import com.nikpanfilov.main.presentation.MainRouter
import com.nikpanfilov.movie.presentation.MovieRouter
import com.nikpanfilov.profile.presentation.ProfileRouter
import com.nikpanfilov.signin.presentation.SignInRouter
import com.nikpanfilov.signup.presentation.SignUpRouter
import com.nikpanfilov.splash.presentation.SplashRouter
import com.nikpanfilov.worldcinema.navigation.ChatRouterImpl
import com.nikpanfilov.worldcinema.navigation.ChatsListRouterImpl
import com.nikpanfilov.worldcinema.navigation.ChooseIconRouterImpl
import com.nikpanfilov.worldcinema.navigation.CollectionMoviesRouterImpl
import com.nikpanfilov.worldcinema.navigation.CollectionsRouterImpl
import com.nikpanfilov.worldcinema.navigation.CompilationRouterImpl
import com.nikpanfilov.worldcinema.navigation.EditCollectionRouterImpl
import com.nikpanfilov.worldcinema.navigation.EpisodeRouterImpl
import com.nikpanfilov.worldcinema.navigation.MainActivityRouterImpl
import com.nikpanfilov.worldcinema.navigation.MainRouterImpl
import com.nikpanfilov.worldcinema.navigation.MovieRouterImpl
import com.nikpanfilov.worldcinema.navigation.ProfileRouterImpl
import com.nikpanfilov.worldcinema.navigation.SignInRouterImpl
import com.nikpanfilov.worldcinema.navigation.SignUpRouterImpl
import com.nikpanfilov.worldcinema.navigation.SplashRouterImpl
import com.nikpanfilov.worldcinema.presentation.MainActivityRouter
import org.koin.dsl.module

val routerModule = module {
	factory<MainActivityRouter> { MainActivityRouterImpl(get()) }
	factory<SplashRouter> { SplashRouterImpl(get()) }
	factory<SignInRouter> { SignInRouterImpl(get()) }
	factory<SignUpRouter> { SignUpRouterImpl(get()) }
	factory<MainRouter> { MainRouterImpl(get()) }
	factory<MovieRouter> { MovieRouterImpl(get()) }
	factory<EpisodeRouter> { EpisodeRouterImpl(get()) }
	factory<ProfileRouter> { ProfileRouterImpl(get()) }
	factory<CollectionsRouter> { CollectionsRouterImpl(get()) }
	factory<CollectionMoviesRouter> { CollectionMoviesRouterImpl(get()) }
	factory<EditCollectionRouter> { EditCollectionRouterImpl(get()) }
	factory<ChooseIconRouter> { ChooseIconRouterImpl(get()) }
	factory<CompilationRouter> { CompilationRouterImpl(get()) }
	factory<ChatsListRouter> { ChatsListRouterImpl(get()) }
	factory<ChatRouter> { ChatRouterImpl(get()) }
}
