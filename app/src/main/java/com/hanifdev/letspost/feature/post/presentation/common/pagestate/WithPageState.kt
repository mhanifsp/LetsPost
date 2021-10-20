package com.hanifdev.letspost.feature.post.presentation.common.pagestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
fun WithPageState(
    pageState: State<PageState>,
    IdleView: (@Composable () -> Unit) = { },
    errorMessage: String = "Oops! Something went wrong, Please try again after some time",
    emptyMessage: String = "Nothing in here Yet!, Please comeback later",
    onRetry: () -> Unit = {},
    content: @Composable () -> Unit
) {
    when (pageState.value) {
        PageState.LOADING -> {
            LoadingView()
        }
        PageState.ERROR -> {
            ErrorView(errorMessage, onRetry)
        }
        PageState.DATA -> {
            content()
        }
        PageState.EMPTY -> {
            EmptyView(emptyMessage)
        }
        PageState.IDLE -> {
            IdleView.invoke()
        }
    }
}