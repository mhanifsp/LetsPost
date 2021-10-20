package com.hanifdev.letspost.feature.post.presentation.postdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanifdev.letspost.feature.post.presentation.Screens
import com.hanifdev.letspost.feature.post.presentation.posts.PostsViewModel

@Composable
fun PostDetailsScreen(
    navController: NavController,
    viewModel: PostDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(end = 32.dp)
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSurface,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = state.content,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            overflow = TextOverflow.Ellipsis
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Button(onClick = {
            if (state.id != -1L) {
                navController.navigate(
                    Screens.AddEditPostScreen.route +
                            "?id=${state.id}"
                )
            }
        }){}

        Button(onClick = {
            navController.navigateUp()
        }) {}

    }
}