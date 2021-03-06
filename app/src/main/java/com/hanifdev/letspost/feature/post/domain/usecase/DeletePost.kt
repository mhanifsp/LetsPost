package com.hanifdev.letspost.feature.post.domain.usecase

import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.model.ApiPostBody
import com.hanifdev.letspost.feature.post.domain.model.Post
import com.hanifdev.letspost.feature.post.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class DeletePost (private val repo: PostRepository) {
    suspend operator fun invoke(id: Long): Flow<BaseResult<Post, Int>> {
        return repo.deletePost(id)
    }
}