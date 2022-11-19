package com.dj.baeminpractice2.domain.repository

import com.dj.baeminpractice2.domain.BmResult
import com.dj.baeminpractice2.domain.model.Term

interface TermsRepository {

    suspend fun getServiceTerm(): BmResult<List<Term>>

    suspend fun getLocationServiceTerm(): BmResult<List<Term>>

}