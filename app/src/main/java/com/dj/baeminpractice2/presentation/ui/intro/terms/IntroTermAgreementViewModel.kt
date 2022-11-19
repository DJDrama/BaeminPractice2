package com.dj.baeminpractice2.presentation.ui.intro.terms

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IntroTermAgreementViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(IntroTermAgreementUiState())
    val uiState = _uiState.asStateFlow()

    fun handleIntent(intent: IntroTermAgreementIntent) {
        when (intent) {
            IntroTermAgreementIntent.AgreeAllTerms -> agreeAllTerms()
            IntroTermAgreementIntent.AgreeLocationTerm -> agreeLocationTerm()
            IntroTermAgreementIntent.AgreeMarketingPushTerm -> agreeMarketingPushTerm()
        }
    }

    private fun agreeAllTerms() {
        val state = uiState.value
        if (!state.isLocationTermAgreed || !state.isMarketingPushTermAgreed) {
            _uiState.update {
                IntroTermAgreementUiState(
                    isLocationTermAgreed = true,
                    isMarketingPushTermAgreed = true
                )
            }
        } else {
            _uiState.update {
                IntroTermAgreementUiState()
            }
        }
    }

    private fun agreeLocationTerm() {
        _uiState.update {
            it.copy(isLocationTermAgreed = !it.isLocationTermAgreed)
        }
    }

    private fun agreeMarketingPushTerm() {
        _uiState.update {
            it.copy(isMarketingPushTermAgreed = !it.isMarketingPushTermAgreed)
        }
    }
}

data class IntroTermAgreementUiState(
    val isLocationTermAgreed: Boolean = false,
    val isMarketingPushTermAgreed: Boolean = false,
)

sealed interface IntroTermAgreementIntent {
    object AgreeAllTerms : IntroTermAgreementIntent
    object AgreeLocationTerm : IntroTermAgreementIntent
    object AgreeMarketingPushTerm : IntroTermAgreementIntent
}