package com.hanifdev.letspost.feature.post.data.repository

import com.hanifdev.letspost.feature.post.data.source.RetrofitService
import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.model.ApiPostBody
import com.hanifdev.letspost.feature.post.domain.model.Post
import com.hanifdev.letspost.feature.post.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl(
    private val api: RetrofitService
) : PostRepository{
    override fun getPosts(): Flow<BaseResult<List<Post>, Int>> {
        return flow{
            val response = api.getPosts()
            if(response.isSuccessful){
                val body = response.body()!!
                emit(BaseResult.Success(body))
            }else{
                val errCode = response.code()
                emit(BaseResult.Error(errCode))
            }
        }
    }

    override suspend fun getPostById(id: Long): Flow<BaseResult<Post, Int>> {
        return flow{
            val response = api.getPostById(id)
            if(response.isSuccessful){
                val body = response.body()!!
                emit(BaseResult.Success(body))
            }else{
                val errCode = response.code()
                emit(BaseResult.Error(errCode))
            }
        }
    }

    override suspend fun addPost(post: ApiPostBody): Flow<BaseResult<Post, Int>> {
        return flow{
            val response = api.addPost(post)
            if(response.isSuccessful){
                val body = response.body()!!
                emit(BaseResult.Success(body))
            }else{
                val errCode = response.code()
                emit(BaseResult.Error(errCode))
            }
        }
    }

    override suspend fun deletePost(id: Long, post: ApiPostBody): Flow<BaseResult<Post, Int>> {
        return flow{
            val response = api.deletePost(id, post)
            if(response.isSuccessful){
                val body = response.body()!!
                emit(BaseResult.Success(body))
            }else{
                val errCode = response.code()
                emit(BaseResult.Error(errCode))
            }
        }
    }

    override suspend fun updatePost(id: Long, post: ApiPostBody): Flow<BaseResult<Post, Int>> {
        return flow{
            val response = api.updatePost(id, post)
            if(response.isSuccessful){
                val body = response.body()!!
                emit(BaseResult.Success(body))
            }else{
                val errCode = response.code()
                emit(BaseResult.Error(errCode))
            }
        }
    }

}