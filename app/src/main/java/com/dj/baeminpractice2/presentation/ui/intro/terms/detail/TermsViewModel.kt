package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dj.baeminpractice2.presentation.utils.BUNDLE_KEY_VIEWPAGER_POSITION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TermsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val initialPosition = 0
    private val _uiState = MutableStateFlow(initialPosition)
    val uiState = _uiState.asStateFlow()

    init {
        handleArguments()
    }

    private fun handleArguments() {
        savedStateHandle.get<Int>(BUNDLE_KEY_VIEWPAGER_POSITION)?.let { position ->
            _uiState.update {
                position
            }
        }
    }
}
