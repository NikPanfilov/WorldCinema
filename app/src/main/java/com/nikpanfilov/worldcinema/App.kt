package com.nikpanfilov.worldcinema

import android.app.Application
import com.nikpanfilov.worldcinema.di.appModule
import com.nikpanfilov.worldcinema.di.mainModule
import com.nikpanfilov.worldcinema.di.networkModule
import com.nikpanfilov.worldcinema.di.routerModule
import com.nikpanfilov.worldcinema.di.signInModule
import com.nikpanfilov.worldcinema.di.signUpModule
import com.nikpanfilov.worldcinema.di.splashModule
import com.nikpanfilov.worldcinema.di.tokenModule
import com.nikpanfilov.worldcinema.di.validatorsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	companion object {

		const val BACKEND = "BACKEND"
		private const val BACKEND_ENDPOINT = "http://107684.web.hosting-russia.ru:8000/"

		internal lateinit var INSTANCE: App
			private set
	}

	override fun onCreate() {
		super.onCreate()

		INSTANCE = this

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)
			properties(mapOf("BACKEND" to BACKEND_ENDPOINT))
			androidFileProperties()

			modules(appModule)
			modules(routerModule)
			modules(networkModule)
			modules(tokenModule)
			modules(validatorsModule)

			modules(splashModule)
			modules(signInModule)
			modules(signUpModule)
			modules(mainModule)
		}
	}
}
