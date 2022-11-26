package com.dj.baeminpractice2.data.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.dj.baeminpractice2.data.model.remote.TermApiModel
import java.time.LocalDate
import java.util.*

private val fakeContent =
    """제 1조 (목적)
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.

제 2조 (용어의 정의)
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.

제 3조 (용어의 정의)
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.약관 관련 내용입니다.
"""

@RequiresApi(Build.VERSION_CODES.O)
val BaeminServiceTerms = listOf<TermApiModel>(
    TermApiModel(8, fakeContent, date = LocalDate.of(2022, 11, 22)),
    TermApiModel(7, fakeContent, date = LocalDate.of(2022, 10, 22)),
    TermApiModel(6, fakeContent, date = LocalDate.of(2022, 9, 30)),
    TermApiModel(5, fakeContent, date = LocalDate.of(2022, 9, 24)),
    TermApiModel(4, fakeContent, date = LocalDate.of(2022, 8, 15)),
    TermApiModel(3, fakeContent, date = LocalDate.of(2022, 8, 12)),
    TermApiModel(2, fakeContent, date = LocalDate.of(2022, 7, 24)),
    TermApiModel(1, fakeContent, date = LocalDate.of(2022, 4, 13)),
)

@RequiresApi(Build.VERSION_CODES.O)
val LocationServiceTerms = listOf<TermApiModel>(
    TermApiModel(4, fakeContent, date = LocalDate.of(2022, 9, 22)),
    TermApiModel(3, fakeContent, date = LocalDate.of(2022, 8, 12)),
    TermApiModel(2, fakeContent, date = LocalDate.of(2022, 7, 24)),
    TermApiModel(1, fakeContent, date = LocalDate.of(2022, 4, 13)),
)