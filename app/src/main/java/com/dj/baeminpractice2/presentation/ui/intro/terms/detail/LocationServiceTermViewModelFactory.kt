package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dj.baeminpractice2.domain.usecase.GetLocationServiceTermsUseCase

class LocationServiceTermViewModelFactory
constructor(
    private val getLocationServiceTermsUseCase: GetLocationServiceTermsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LocationServiceTermViewModel::class.java)) {
            LocationServiceTermViewModel(getLocationServiceTermsUseCase = getLocationServiceTermsUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}