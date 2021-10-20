package com.hanifdev.letspost.feature.post.data.source

import com.hanifdev.letspost.feature.post.domain.model.ApiPostBody
import com.hanifdev.letspost.feature.post.domain.model.Post
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Long): Response<Post>

    @POST("posts")
    suspend fun addPost(@Body body: ApiPostBody): Response<Post>

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Long, @Body body: ApiPostBody): Response<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Long, @Body body: ApiPostBody): Response<Post>
}