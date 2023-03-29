package com.nikpanfilov.extensions

import android.os.Looper
import android.widget.EditText
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun EditText.bindText(
	scope: LifecycleCoroutineScope,
	flow: MutableStateFlow<String?>,
) {
	check(Looper.myLooper() == Looper.getMainLooper()) {
		"flow binding run in ${Thread.currentThread().name}, but must be in main"
	}

	scope.launchWhenResumed {
		flow.filter {
			text?.toString() != it
		}.onEach {
			setText(it)
		}
	}

	scope.launchWhenResumed {
		asFlow().filter {
			it != flow.value
		}.onEach {
			flow.value = it
		}.collect()
	}
}

fun <T> Flow<T>.launch(scope: LifecycleCoroutineScope, action: (T) -> Unit) = scope.launch { onEach { action(it) }.collect() }