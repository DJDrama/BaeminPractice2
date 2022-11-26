package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.baeminpractice2.presentation.model.TermPresentationModel
import com.dj.baeminpractice2.presentation.utils.BUNDLE_KEY_TERMS
import com.dj.baeminpractice2.presentation.utils.BUNDLE_KEY_TERM_ID
import com.dj.baeminpractice2.presentation.utils.BUNDLE_KEY_TERM_TYPE
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TermsListViewModel
constructor(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _uiState = MutableStateFlow(TermsListUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<TermsListUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var termType: Int = 0

    init {
        getArgumentsFromSavedState()
    }

    fun handleIntent(intent: TermsListIntent) {
        when (intent) {
            is TermsListIntent.OnChooseTermItem -> onChooseTermItem(termPresentationModel = intent.termPresentationModel)
        }
    }

    private fun onChooseTermItem(termPresentationModel: TermPresentationModel) {
        viewModelScope.launch {
            _uiEvent.emit(
                value = TermsListUiEvent.ChosenTermItem(
                    termPresentationModel = termPresentationModel,
                    termType = termType
                )
            )
        }
    }

    private fun getArgumentsFromSavedState() {
        termType = savedStateHandle.get<Int>(BUNDLE_KEY_TERM_TYPE) ?: 0
        savedStateHandle.get<List<TermPresentationModel>>(BUNDLE_KEY_TERMS)?.let { terms ->
            _uiState.update {
                return@update it.copy(
                    terms = terms.map { model ->
                        model.copy(isSelected = model.id == savedStateHandle[BUNDLE_KEY_TERM_ID])
                    },
                )
            }
        }
    }

}

data class TermsListUiState(
    val terms: List<TermPresentationModel> = emptyList(),
    val selectedTermPosition: Int = 0
)

sealed interface TermsListIntent {
    data class OnChooseTermItem(val termPresentationModel: TermPresentationModel) : TermsListIntent
}

sealed interface TermsListUiEvent {
    data class ChosenTermItem(val termPresentationModel: TermPresentationModel, val termType: Int) :
        TermsListUiEvent
}