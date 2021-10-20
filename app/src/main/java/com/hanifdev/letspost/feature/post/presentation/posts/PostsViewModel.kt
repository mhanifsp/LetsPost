package com.hanifdev.letspost.feature.post.presentation.posts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.usecase.PostsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCases
): ViewModel () {
    private val _state = mutableStateOf(PostsState())
    val state: State<PostsState> = _state

    private var job: Job? = null

    init {
        getPosts()
    }

    private fun getPosts() {
        job?.cancel()
        job = viewModelScope.launch {
            postsUseCase.getPosts()
                .onStart {
//                    setLoading()
                }
                .catch { exception ->
//                    hideLoading()
//                    showToast(exception.message.toString())
                }.collect { baseResult ->

                    when(baseResult){
                        is BaseResult.Success -> {
                            _state.value = state.value.copy(
                                posts = baseResult.data
                            )
                        }
                        is BaseResult.Error -> {

                        }
                    }

                }

        }
    }
}