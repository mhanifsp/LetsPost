package com.hanifdev.letspost.feature.post.domain

sealed class BaseResult <out T : Any, out U : Any> {
    data class Success <T: Any>(val data : T) : BaseResult<T, Nothing>()
    data class Error <U : Any>(val code: U) : BaseResult<Nothing, U>()
}