package com.nikpanfilov.core.network

import retrofit2.Retrofit

inline fun <reified T> createRetrofitService(retrofit: Retrofit): T =
	retrofit.create(T::class.java)