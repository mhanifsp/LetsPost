package com.hanifdev.letspost.feature.post.domain.usecase

import com.hanifdev.letspost.feature.post.domain.BaseResult
import com.hanifdev.letspost.feature.post.domain.model.Post
import com.hanifdev.letspost.feature.post.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPosts (private val repo: PostRepository) {
    operator fun invoke() : Flow<BaseResult<List<Post>, Int>> {
        return repo.getPosts()
    }
}