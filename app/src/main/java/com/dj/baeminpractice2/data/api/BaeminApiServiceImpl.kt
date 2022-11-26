package com.dj.baeminpractice2.data.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.dj.baeminpractice2.data.model.remote.TermApiModel
import com.dj.baeminpractice2.domain.BmResult

class BaeminApiServiceImpl : BaeminApiService {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getServiceTerm(): BmResult<List<TermApiModel>> {
        return BmResult.Success(data = BaeminServiceTerms)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getLocationServiceTerm(): BmResult<List<TermApiModel>> {
        return BmResult.Success(data = LocationServiceTerms)
    }

}