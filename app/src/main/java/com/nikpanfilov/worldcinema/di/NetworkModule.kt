package com.nikpanfilov.worldcinema.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.nikpanfilov.core.network.interceptors.loggingInterceptor
import com.nikpanfilov.core.network.interceptors.noConnectionInterceptor
import com.nikpanfilov.core.network.interceptors.tokenInterceptor
import com.nikpanfilov.core.network.provider.provideMoshi
import com.nikpanfilov.core.network.provider.provideOkHttpClient
import com.nikpanfilov.core.network.provider.provideRetrofit
import com.nikpanfilov.worldcinema.App.Companion.BACKEND

const val LOG_INTERCEPTOR = "logInterceptor"
const val NO_CONNECT_INTERCEPTOR = "noConnectionInterceptor"
const val TOKEN_INTERCEPTOR = "tokenInterceptor"
const val TOKEN_AUTHENTICATOR = "tokenAuthenticator"
const val ORIGINAL = "original"

val networkModule = module {
	single(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
	single(named(NO_CONNECT_INTERCEPTOR)) { noConnectionInterceptor(androidContext()) }
	single(named(TOKEN_INTERCEPTOR)) { tokenInterceptor(get()) }

	single { provideMoshi() }

	single(named(ORIGINAL)) {
		provideOkHttpClient(
			logging = get(named(LOG_INTERCEPTOR)),
			noConnection = get(named(NO_CONNECT_INTERCEPTOR)),
			token = get(named(TOKEN_INTERCEPTOR)),
			tokenAuthenticator = get(named(TOKEN_AUTHENTICATOR))
		)
	}
	single(named(ORIGINAL)) {
		provideRetrofit(
			okHttpClient = get(named(ORIGINAL)),
			moshi = get(),
			url = getProperty(BACKEND)
		)
	}
}