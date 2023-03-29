package com.nikpanfilov.core.network.interceptors

import android.util.Log
import com.nikpanfilov.core.network.token.domain.usecase.LoadTokenUseCase
import okhttp3.Interceptor

fun tokenInterceptor(loadTokenUseCase: LoadTokenUseCase): Interceptor {
	return Interceptor { chain ->

		val request = chain.request()
			.newBuilder()
			.addHeader("Authorization", "Bearer ${loadTokenUseCase().accessToken}")
			.build()
		chain.proceed(request)
	}
}

fun refreshTokenInterceptor(loadTokenUseCase: LoadTokenUseCase): Interceptor {
	return Interceptor { chain ->

		val request = chain.request()
			.newBuilder()
			.addHeader("Authorization", "Bearer ${loadTokenUseCase().refreshToken}")
			.build()
		chain.proceed(request)
	}
}