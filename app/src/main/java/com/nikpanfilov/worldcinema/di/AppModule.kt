package com.nikpanfilov.worldcinema.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.worldcinema.navigation.GlobalRouterImpl
import com.nikpanfilov.worldcinema.navigation.GlobalRouterName.GLOBAL
import com.nikpanfilov.worldcinema.navigation.buildCicerone
import com.nikpanfilov.worldcinema.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named(GLOBAL)) { buildCicerone() }
    single(named(GLOBAL)) { get<Cicerone<Router>>(named(GLOBAL)).router }
    single(named(GLOBAL)) { get<Cicerone<Router>>(named(GLOBAL)).getNavigatorHolder() }
    single<GlobalRouter> { GlobalRouterImpl(get(named(GLOBAL))) }

    viewModel {
        MainViewModel(get())
    }
}
