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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(PostDetailsState())
    val state: State<PostDetailsState> = _state

    init {
        savedStateHandle.get<Int>("id")?.let{ id ->
            getPostById(id.toLong())
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch {
            postsUseCase.deletePost(
                post.id,
                ApiPostBody(post.title, post.content)
            )
        }
    }

    fun getPostById(id: Long){
        if(id != -1L) {
            viewModelScope.launch {
                postsUseCase.getPostById(id.toLong())
                    .collect { baseResult ->
                        when(baseResult){
                            is BaseResult.Success -> {
                                _state.value = state.value.copy(
                                    post = baseResult.data
                                )
                            }
                            is BaseResult.Error -> {

                            }
                        }
                    }
            }
        }
    }



}