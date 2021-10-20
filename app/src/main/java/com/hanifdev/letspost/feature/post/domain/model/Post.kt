package com.hanifdev.letspost.feature.post.domain.model

data class Post(
    val content: String,
    val created_at: String,
    val id: Int,
    val published_at: String,
    val title: String,
    val updated_at: String
)