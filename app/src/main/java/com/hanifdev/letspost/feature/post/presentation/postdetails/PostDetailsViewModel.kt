package com.hanifdev.letspost.feature.post.presentation.postdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.model.ApiPostBody
import com.hanifdev.letspost.feature.post.domain.model.Post
import com.hanifdev.letspost.feature.post.domain.usecase.PostsUseCases
import com.hanifdev.letspost.feature.post.presentation.addeditpost.AddEditPostViewModel
import com.hanifdev.letspost.feature.post.presentation.common.pagestate.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(PostDetailsState())
    val state: State<PostDetailsState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _pageState = mutableStateOf(PageState.IDLE)
    val pageState: State<PageState> = _pageState
    val id: Int? = savedStateHandle.get<Int>("id")

    init {
        getPostById()
    }

    fun retry(){
        getPostById()
    }

    private fun setLoading(){
        _pageState.value = PageState.LOADING
    }

    fun deletePost(post: Post) {
        viewModelScope.launch {
            postsUseCase.deletePost(post.id)
                .onStart {
                    setLoading()
                }.collect()
            _eventFlow.emit(UiEvent.DeletePost)
        }
    }

    fun getPostById(){
        id?.let {
            if(id != -1) {
                viewModelScope.launch {
                    postsUseCase.getPostById(id.toLong())
                        .onStart {
                            setLoading()
                        }
                        .collect { baseResult ->
                            when(baseResult){
                                is BaseResult.Success -> {
                                    _pageState.value = PageState.DATA
                                    _state.value = state.value.copy(
                                        post = baseResult.data
                                    )
                                }
                                is BaseResult.Error -> {
                                    _pageState.value = PageState.ERROR
                                }
                            }
                        }
                }

            }
        }

    }

    sealed class UiEvent {
        object DeletePost: UiEvent()
    }

}