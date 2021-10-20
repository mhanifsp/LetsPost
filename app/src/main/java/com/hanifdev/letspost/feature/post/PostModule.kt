package com.hanifdev.letspost.feature.post

import com.hanifdev.letspost.di.AppModule
import com.hanifdev.letspost.feature.post.data.repository.PostRepositoryImpl
import com.hanifdev.letspost.feature.post.data.source.RetrofitService
import com.hanifdev.letspost.feature.post.domain.repository.PostRepository
import com.hanifdev.letspost.feature.post.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
class PostModule {
    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(retrofitService: RetrofitService): PostRepository {
        return PostRepositoryImpl(retrofitService)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: PostRepository): PostsUseCases {
        return PostsUseCases(
            getPosts = GetPosts(repository),
            getPostById = GetPostById(repository),
            updatePost = UpdatePost(repository),
            deletePost = DeletePost(repository),
            addPost = AddPost(repository)
        )
    }
}