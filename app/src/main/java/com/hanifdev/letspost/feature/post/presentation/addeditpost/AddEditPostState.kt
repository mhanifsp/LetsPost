package com.hanifdev.letspost.feature.post.presentation.addeditpost

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanifdev.letspost.feature.post.domain.model.Post

data class AddEditPostState (
    val id: Long = -1,
    val title: String = "",
    val content: String = ""
)