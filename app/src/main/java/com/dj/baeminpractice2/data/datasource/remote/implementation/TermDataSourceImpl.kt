package com.dj.baeminpractice2.data.datasource.remote.implementation

import com.dj.baeminpractice2.data.api.BaeminApiService
import com.dj.baeminpractice2.data.datasource.remote.TermDataSource
import com.dj.baeminpractice2.data.model.remote.TermApiModel
import com.dj.baeminpractice2.domain.BmResult

class TermDataSourceImpl
constructor(
    private val baeminApiService: BaeminApiService
) : TermDataSource {
    override suspend fun getServiceTerm(): BmResult<List<TermApiModel>> {
        return baeminApiService.getServiceTerm()
    }

    override suspend fun getLocationServiceTerm(): BmResult<List<TermApiModel>> {
        return baeminApiService.getLocationServiceTerm()
    }
}