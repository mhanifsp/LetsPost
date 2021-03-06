package com.hanifdev.letspost.feature.post.presentation.posts

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanifdev.letspost.feature.post.presentation.Screens
import com.hanifdev.letspost.feature.post.presentation.common.observeAsSate
import com.hanifdev.letspost.feature.post.presentation.common.pagestate.WithPageState
import com.hanifdev.letspost.feature.post.presentation.postdetails.PostDetailsViewModel
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun PostsScreen(
    navController: NavController,
    viewModel: PostsViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val pagestate = viewModel.pageState

    val lifeCycleState = LocalLifecycleOwner.current.lifecycle.observeAsSate()
    val state2 = lifeCycleState.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddEditPostScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add post")
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
            ) {
                Text(
                    text = "Lets Post",
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(state.posts) { post ->
                        PostItem(
                            post = post,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                navController.navigate(
                                    Screens.PostDetailsScreen.route +
                                            "?id=${post.id}"
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

}
