package com.dj.baeminpractice2.domain.usecase

import com.dj.baeminpractice2.domain.BmResult
import com.dj.baeminpractice2.domain.model.Term
import com.dj.baeminpractice2.domain.repository.TermsRepository

class GetLocationServiceTermsUseCase
constructor(
    private val repository: TermsRepository
) {
    suspend operator fun invoke(): BmResult<List<Term>> {
        return repository.getLocationServiceTerm()
    }
}