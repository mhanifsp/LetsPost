package com.hanifdev.letspost.feature.post.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hanifdev.letspost.feature.post.presentation.addeditpost.AddEditPostScreen
import com.hanifdev.letspost.feature.post.presentation.postdetails.PostDetailsScreen
import com.hanifdev.letspost.feature.post.presentation.posts.PostsScreen
import com.hanifdev.letspost.ui.theme.LetsPostTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsPostTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.PostsScreen.route
                    ) {
                        composable(route = Screens.PostsScreen.route) {
                            PostsScreen(navController = navController)
                        }
                        composable(
                            route = Screens.AddEditPostScreen.route +
                                    "?id={id}",
                            arguments = listOf(
                                navArgument(
                                    name = "id"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            AddEditPostScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screens.PostDetailsScreen.route +
                                    "?id={id}",
                            arguments = listOf(
                                navArgument(
                                    name = "id"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            PostDetailsScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}