package com.hanifdev.letspost.feature.post.presentation.posts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.usecase.PostsUseCases
import com.hanifdev.letspost.feature.post.presentation.common.pagestate.PageState
import com.hanifdev.letspost.feature.post.presentation.postdetails.PostDetailsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCases
): ViewModel () {
    private val _state = mutableStateOf(PostsState())
    val state: State<PostsState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _pageState = mutableStateOf(PageState.IDLE)
    val pageState: State<PageState> = _pageState

    private var job: Job? = null

    init {
        getPosts()
    }

    fun retry(){
        getPosts()
    }

    private fun setLoading(){
        _pageState.value = PageState.LOADING
    }

    private fun getPosts() {
        job?.cancel()
        job = viewModelScope.launch {
            postsUseCase.getPosts()
                .onStart {
                    setLoading()
                }
                .collect { baseResult ->

                    when(baseResult){
                        is BaseResult.Success -> {
                            _state.value = state.value.copy(
                                posts = baseResult.data
                            )
                            _pageState.value = PageState.DATA
                        }
                        is BaseResult.Error -> {
                            _pageState.value = PageState.ERROR
                            _eventFlow.emit(UiEvent.IsError(true))
                        }
                    }

                }

        }
    }

    sealed class UiEvent {
        data class IsError(val value: Boolean): UiEvent()
    }
}