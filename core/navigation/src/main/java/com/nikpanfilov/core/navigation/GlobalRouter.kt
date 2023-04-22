package com.nikpanfilov.core.navigation

interface GlobalRouter {

	fun open(destination: FragmentDestination)

	fun backTo(destination: FragmentDestination)

	fun replace(fragmentDestination: FragmentDestination)

	fun newRoot(fragmentDestination: FragmentDestination)

	fun popToRoot()

	fun exit()

	fun finish()

	fun popTo(fragmentDestinationClass: Class<out FragmentDestination>)
}