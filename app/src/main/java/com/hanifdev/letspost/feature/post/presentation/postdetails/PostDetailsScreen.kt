package com.hanifdev.letspost.feature.post.presentation.postdetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val scrollState = rememberScrollState()
    val openDialog = remember { mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1F)
        ) {
            Text(
                text = state.post.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Published : ${state.post.published_at}")
            Text(text = "Updated : ${state.post.updated_at}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.post.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Button(
                onClick = {
                    if (state.post.id != -1L) {
                        navController.navigate(
                            Screens.AddEditPostScreen.route +
                                    "?id=${state.post.id}"
                        )
                    }
                },
                modifier = Modifier.weight(1F)
            ){
                Text("Edit")
            }
            
            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    openDialog.value = true
                    navController.navigateUp()
                },
                modifier = Modifier.weight(1F)
            ) {
                Text(text = "Delete")
            }

        }

        if (openDialog.value){
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = { Text(text = "Delete") },
                text = { Text(
                    text = "Are you sure you want to delete?"
                ) },

                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            viewModel.deletePost(state.post)
                            navController.navigateUp()
                        },
                        colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.White)
                    ){
                        Text(text = "Yes")
                    }
                },

                dismissButton = {
                    Button(onClick = {
                        openDialog.value = false
                    }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}