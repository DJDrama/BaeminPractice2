package com.dj.baeminpractice2.data.repository.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.dj.baeminpractice2.data.datasource.remote.TermDataSource
import com.dj.baeminpractice2.data.model.remote.asDomainList
import com.dj.baeminpractice2.domain.BmResult
import com.dj.baeminpractice2.domain.model.Term
import com.dj.baeminpractice2.domain.repository.TermsRepository

class TermsRepositoryImpl
constructor(
    private val termDataSource: TermDataSource
) : TermsRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getServiceTerm(): BmResult<List<Term>> {
        return when (val response = termDataSource.getServiceTerm()) {
            is BmResult.Success -> BmResult.Success(data = response.data.asDomainList())
            is BmResult.Error -> BmResult.Error(exception = NullPointerException())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getLocationServiceTerm(): BmResult<List<Term>> {
        return when (val response = termDataSource.getLocationServiceTerm()) {
            is BmResult.Success -> BmResult.Success(data = response.data.asDomainList())
            is BmResult.Error -> BmResult.Error(exception = NullPointerException())
        }
    }

}