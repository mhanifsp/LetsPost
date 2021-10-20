package com.hanifdev.letspost.feature.post.presentation.addeditpost

sealed class AddEditPostEvents{
    data class EnteredTitle(val value: String): AddEditPostEvents()
    data class EnteredContent(val value: String): AddEditPostEvents()
    object SavePost: AddEditPostEvents()
}