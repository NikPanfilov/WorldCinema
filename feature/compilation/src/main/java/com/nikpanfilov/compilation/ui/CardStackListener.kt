package com.nikpanfilov.compilation.ui

import android.view.View
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class CardStackListener(private val onSwap: (Direction?) -> Unit, private val setCurrentCard: (Int) -> Unit,private val checkEmpty:(Int)->Unit) : CardStackListener {

	override fun onCardDragging(direction: Direction?, ratio: Float) {
		//
	}

	override fun onCardSwiped(direction: Direction?) {
		onSwap(direction)
	}

	override fun onCardRewound() {
		//
	}

	override fun onCardCanceled() {
		//
	}

	override fun onCardAppeared(view: View?, position: Int) {
		setCurrentCard(position)
	}

	override fun onCardDisappeared(view: View?, position: Int) {
		checkEmpty(position)
	}
}