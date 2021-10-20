package com.hanifdev.letspost.feature.post.presentation.posts

import com.hanifdev.letspost.feature.post.domain.model.Post

data class PostsState(
    val posts: List<Post> = emptyList()
)