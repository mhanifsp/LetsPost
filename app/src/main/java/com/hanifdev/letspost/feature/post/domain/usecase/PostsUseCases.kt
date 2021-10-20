package com.hanifdev.letspost.feature.post.domain.usecase

data class PostsUseCases (
    val addPost: AddPost,
    val getPosts: GetPosts,
    val updatePost: UpdatePost,
    val deletePost: DeletePost,
    val getPostById: GetPostById
)