package com.dj.baeminpractice2.data.model.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.dj.baeminpractice2.domain.model.Term
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class TermApiModel(
    val id: Int,
    val content: String,
    val date: LocalDate
)

@RequiresApi(Build.VERSION_CODES.O)
fun TermApiModel.asDomainModel(): Term {
    return Term(
        id = id,
        title = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 시행안").format(date),
        content = content
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun List<TermApiModel>.asDomainList(): List<Term> = this.sortedByDescending { it.id }
    .map {
        it.asDomainModel()
    }
