package com.hanifdev.letspost.feature.post.presentation.addeditpost

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanifdev.letspost.feature.post.presentation.Screens
import com.hanifdev.letspost.feature.post.presentation.common.pagestate.WithPageState
import com.hanifdev.letspost.feature.post.presentation.postdetails.PostDetailsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditPostScreen(
    navController: NavController,
    viewModel: AddEditPostViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val pagestate = viewModel.pageState

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditPostViewModel.UiEvent.SavePost -> {
                    navController.navigate(
                        Screens.PostsScreen.route
                    ){
                        popUpTo(Screens.AddEditPostScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditPostEvents.SavePost)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        WithPageState(
            pageState = pagestate,
            onRetry = { viewModel.retry() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                TextField(
                    value = state.title,
                    label = {
                        Text(text = "Title")
                    },
                    onValueChange = {
                        viewModel.onEvent(AddEditPostEvents.EnteredTitle(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.content,
                    label = {
                        Text(text = "Content")
                    },
                    onValueChange = {
                        viewModel.onEvent(AddEditPostEvents.EnteredContent(it))
                    },
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxWidth()
                )
            }
        }
    }
}