package com.nikpanfilov.compilation

import com.nikpanfilov.compilation.ui.CompilationFragment
import com.nikpanfilov.core.navigation.FragmentDestination

class CompilationDestination : FragmentDestination {

	override fun createInstance() = CompilationFragment.newInstance()
}