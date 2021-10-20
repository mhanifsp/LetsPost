package com.hanifdev.letspost.feature.post.presentation.postdetails

data class PostDetailsState (
    val id: Long = -1,
    val title: String = "",
    val content: String = "",
    val updatedAt: String = "",
    val publishedAt: String = ""
)