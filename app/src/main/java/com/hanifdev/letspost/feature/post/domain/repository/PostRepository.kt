package com.hanifdev.letspost.feature.post.domain.repository

import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.model.ApiPostBody
import com.hanifdev.letspost.feature.post.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(): Flow<BaseResult<List<Post>, Int>>

    suspend fun getPostById(id: Long): Flow<BaseResult<Post, Int>>

    suspend fun addPost(post: ApiPostBody): Flow<BaseResult<Post, Int>>

    suspend fun deletePost(id: Long): Flow<BaseResult<Post, Int>>

    suspend fun updatePost(id: Long, post: ApiPostBody): Flow<BaseResult<Post, Int>>

}