package com.dj.baeminpractice2.data.datasource.remote

import com.dj.baeminpractice2.data.model.remote.TermApiModel
import com.dj.baeminpractice2.domain.BmResult


interface TermDataSource {

    suspend fun getServiceTerm(): BmResult<List<TermApiModel>>

    suspend fun getLocationServiceTerm(): BmResult<List<TermApiModel>>
}