package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.baeminpractice2.domain.BmResult
import com.dj.baeminpractice2.domain.model.Term
import com.dj.baeminpractice2.domain.usecase.GetLocationServiceTermsUseCase
import com.dj.baeminpractice2.presentation.model.TermPresentationModel
import com.dj.baeminpractice2.presentation.model.asPresentationList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LocationServiceTermViewModel
constructor(
    private val getLocationServiceTermsUseCase: GetLocationServiceTermsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LocationServiceTermUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LocationServiceTermUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getBaeminServiceTerms()
    }

    fun handleIntent(intent: LocationServiceTermIntent) {
        when (intent) {
            LocationServiceTermIntent.OnClickChooseTerms -> onClickChooseTerms()
            is LocationServiceTermIntent.ShowChosenTermContent -> showChosenTermContent(
                termPresentationModel = intent.termPresentationModel
            )
        }
    }

    private fun onClickChooseTerms() {
        viewModelScope.launch {
            _uiEvent.emit(
                value = LocationServiceTermUiEvent.ListAllTerms(
                    terms = uiState.value.terms.asPresentationList(),
                    currentTermId = uiState.value.currentTermId
                )
            )
        }
    }

    private fun getBaeminServiceTerms() {
        viewModelScope.launch {
            val response = getLocationServiceTermsUseCase()
            when (response) {
                is BmResult.Success -> {
                    val serviceTerms = response.data
                    val firstServiceTerm = serviceTerms.first()
                    _uiState.update {
                        it.copy(
                            dateTitle = firstServiceTerm.title,
                            content = firstServiceTerm.content,
                            currentTermId = firstServiceTerm.id,
                            terms = serviceTerms,
                        )
                    }
                }
                is BmResult.Error -> TODO()
            }
        }
    }

    private fun showChosenTermContent(termPresentationModel: TermPresentationModel?) {
        termPresentationModel?.let {
            val selectedTerm = uiState.value.terms.firstOrNull { it.id == termPresentationModel.id }
            selectedTerm?.let { term ->
                _uiState.update {
                    it.copy(
                        dateTitle = term.title,
                        content = term.content,
                        currentTermId = term.id
                    )
                }
            }
        }
    }
}

data class LocationServiceTermUiState(
    val dateTitle: String = "",
    val content: String = "",
    val terms: List<Term> = emptyList(),
    val currentTermId: Int = 0
)

sealed interface LocationServiceTermIntent {
    object OnClickChooseTerms : LocationServiceTermIntent
    data class ShowChosenTermContent(val termPresentationModel: TermPresentationModel?) :
        LocationServiceTermIntent
}

sealed interface LocationServiceTermUiEvent {
    data class ListAllTerms(val terms: List<TermPresentationModel>, val currentTermId: Int) :
        LocationServiceTermUiEvent
}