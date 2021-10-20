package com.hanifdev.letspost.feature.post.presentation.addeditpost

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.model.ApiPostBody
import com.hanifdev.letspost.feature.post.domain.usecase.PostsUseCases
import com.hanifdev.letspost.feature.post.presentation.postdetails.PostDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditPostViewModel@Inject constructor(
    private val postsUseCase: PostsUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(AddEditPostState())
    val state: State<AddEditPostState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init{
        savedStateHandle.get<Int>("id")?.let{ id ->
            getPostById(id.toLong())
        }
    }

    fun onEvent(events: AddEditPostEvents){
        when(events){
            is AddEditPostEvents.EnteredTitle -> {
                _state.value = state.value.copy(
                    title = events.value
                )
            }
            is AddEditPostEvents.EnteredContent -> {
                _state.value = state.value.copy(
                    content = events.value
                )
            }
            is AddEditPostEvents.SavePost -> {
                viewModelScope.launch {
                    if (state.value.id == -1L){
                        postsUseCase.addPost(
                            ApiPostBody(state.value.title, state.value.content)
                        ).collect()
                    } else {
                        postsUseCase.updatePost(
                            state.value.id,
                            ApiPostBody(state.value.title, state.value.content)
                        ).collect()
                    }
                    _eventFlow.emit(UiEvent.SavePost)
                }
            }
        }
    }

    private fun getPostById(id: Long){
        if(id != -1L) {
            viewModelScope.launch {
                postsUseCase.getPostById(id)
                    .collect { baseResult ->
                        when(baseResult){
                            is BaseResult.Success -> {
                                _state.value = state.value.copy(
                                    id = baseResult.data.id.toLong(),
                                    title = baseResult.data.title,
                                    content = baseResult.data.content
                                )
                            }
                            is BaseResult.Error -> {

                            }
                        }

                    }
            }
        }
    }

    sealed class UiEvent {
        object SavePost: UiEvent()
    }
}