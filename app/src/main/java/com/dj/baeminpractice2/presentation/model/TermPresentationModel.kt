package com.dj.baeminpractice2.presentation.model

import android.os.Parcelable
import com.dj.baeminpractice2.domain.model.Term
import kotlinx.parcelize.Parcelize

@Parcelize
data class TermPresentationModel(
    val id: Int,
    val title: String,
    val isSelected: Boolean = false,
) : Parcelable

fun Term.asPresentationModel(): TermPresentationModel {
    return TermPresentationModel(
        id = id,
        title = title
    )
}

fun List<Term>.asPresentationList(): List<TermPresentationModel> = map {
    it.asPresentationModel()
}