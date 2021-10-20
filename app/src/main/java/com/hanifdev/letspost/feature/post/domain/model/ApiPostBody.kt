package com.hanifdev.letspost.feature.post.domain.model

import com.google.gson.annotations.SerializedName

data class ApiPostBody(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)
