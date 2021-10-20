package com.hanifdev.letspost.feature.post.domain.repository

import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(): Flow<BaseResult<List<Post>, Int>>

    suspend fun getPostById(id: Long): Flow<BaseResult<Post, Int>>

    suspend fun addPost(post: Post): Flow<BaseResult<Post, Int>>

    suspend fun deletePost(post: Post): Flow<BaseResult<Post, Int>>

    suspend fun updatePost(post: Post): Flow<BaseResult<Post, Int>>

}