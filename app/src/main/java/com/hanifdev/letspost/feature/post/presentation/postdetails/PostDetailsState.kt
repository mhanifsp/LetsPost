package com.hanifdev.letspost.feature.post.presentation.postdetails

import com.hanifdev.letspost.feature.post.domain.model.Post

data class PostDetailsState (
    val post: Post = Post("", "",-1L,"", "","")
)