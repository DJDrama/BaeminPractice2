package com.dj.baeminpractice2.domain

sealed interface BmResult<out T> {
    data class Success<out T>(val data: T) : BmResult<T>
    data class Error(val exception: Exception) : BmResult<Nothing>
}