package com.nikpanfilov.worldcinema.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.nikpanfilov.collections.ui.CollectionsFragment
import com.nikpanfilov.compilation.ui.CompilationFragment
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.main.ui.MainFragment
import com.nikpanfilov.profile.ui.ProfileFragment
import com.nikpanfilov.worldcinema.R
import com.nikpanfilov.worldcinema.databinding.HostBinding
import com.nikpanfilov.worldcinema.navigation.GlobalRouterName.GLOBAL
import com.nikpanfilov.worldcinema.presentation.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity(), OnFragmentChangedListener {

	private val navigatorHolder: NavigatorHolder by inject(named(GLOBAL))
	private val navigator = AppNavigator(this, R.id.host_global)

	private val viewModel by viewModel<MainViewModel>()

	private val binding by lazy { HostBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.host)
	}

	override fun onResume() {
		super.onResume()

		findViewById<View>(R.id.main).setOnClickListener { viewModel.navigateToMain() }
		findViewById<View>(R.id.compilation).setOnClickListener { viewModel.navigateToCompilation() }
		findViewById<View>(R.id.collections).setOnClickListener { viewModel.navigateToCollections() }
		findViewById<View>(R.id.profile).setOnClickListener { viewModel.navigateToProfile() }

		navigatorHolder.setNavigator(navigator)
		viewModel.navigateToSplash()
	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}

	override fun onFragmentChanged(fragment: Fragment) {
		if (fragment is MainFragment ||
			fragment is ProfileFragment ||
			fragment is CollectionsFragment ||
			fragment is CompilationFragment
		)
			findViewById<View>(R.id.bottom_navigation).visibility = View.VISIBLE
		else
			findViewById<View>(R.id.bottom_navigation).visibility = View.GONE
	}
}
