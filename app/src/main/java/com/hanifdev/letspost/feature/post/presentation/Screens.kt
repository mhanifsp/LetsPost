package com.hanifdev.letspost.feature.post.presentation

sealed class Screens(val route: String) {
    object PostsScreen: Screens("posts_screen")
    object AddEditPostScreen: Screens("add_edit_post_screen")
    object PostDetailsScreen: Screens("post_details_screen")
}