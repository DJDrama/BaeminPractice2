package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dj.baeminpractice2.domain.usecase.GetBaeminServiceTermsUseCase


class BaeminServiceTermViewModelFactory
constructor(
    private val getBaeminServiceTermsUseCase: GetBaeminServiceTermsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BaeminServiceTermViewModel::class.java)) {
            BaeminServiceTermViewModel(getBaeminServiceTermsUseCase = getBaeminServiceTermsUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}