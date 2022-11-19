package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.baeminpractice2.domain.BmResult
import com.dj.baeminpractice2.domain.model.Term
import com.dj.baeminpractice2.domain.usecase.GetBaeminServiceTermsUseCase
import com.dj.baeminpractice2.presentation.model.TermPresentationModel
import com.dj.baeminpractice2.presentation.model.asPresentationList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BaeminServiceTermViewModel
constructor(
    private val getBaeminServiceTermsUseCase: GetBaeminServiceTermsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BaeminServiceTermUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<BaeminServiceTermUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getBaeminServiceTerms()
    }

    fun handleIntent(intent: BaeminServiceTermIntent) {
        when (intent) {
            BaeminServiceTermIntent.OnClickChooseTerms -> onClickChooseTerms()
            is BaeminServiceTermIntent.ShowChosenTermContent -> showChosenTermContent(
                termPresentationModel = intent.termPresentationModel
            )
        }
    }

    private fun onClickChooseTerms() {
        viewModelScope.launch {
            _uiEvent.emit(
                value = BaeminServiceTermUiEvent.ListAllTerms(
                    terms = uiState.value.terms.asPresentationList(),
                    currentTermId = uiState.value.currentTermId
                )
            )
        }
    }

    private fun getBaeminServiceTerms() {
        viewModelScope.launch {
            val response = getBaeminServiceTermsUseCase()
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

data class BaeminServiceTermUiState(
    val dateTitle: String = "",
    val content: String = "",
    val terms: List<Term> = emptyList(),
    val currentTermId: Int = 0
)

sealed interface BaeminServiceTermIntent {
    object OnClickChooseTerms : BaeminServiceTermIntent
    data class ShowChosenTermContent(val termPresentationModel: TermPresentationModel?) :
        BaeminServiceTermIntent
}

sealed interface BaeminServiceTermUiEvent {
    data class ListAllTerms(val terms: List<TermPresentationModel>, val currentTermId: Int) :
        BaeminServiceTermUiEvent
}