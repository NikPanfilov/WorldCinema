package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.main.presentation.MainRouter
import com.nikpanfilov.signin.presentation.SignInRouter
import com.nikpanfilov.signup.presentation.SignUpRouter
import com.nikpanfilov.splash.presentation.SplashRouter
import com.nikpanfilov.worldcinema.navigation.MainActivityRouterImpl
import com.nikpanfilov.worldcinema.navigation.MainRouterImpl
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
}
