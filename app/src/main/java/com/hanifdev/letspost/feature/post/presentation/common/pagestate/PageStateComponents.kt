package com.hanifdev.letspost.feature.post.presentation.common.pagestate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingView(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
    }
}

@Composable
fun ErrorView(message: String = "Oops! Something went wrong, Please refresh after some time",onRetry: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Error",
            tint = MaterialTheme.colors.error,
            modifier = Modifier.size(108.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        ErrorText(message)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onRetry) {

        }
    }
}

@Composable
fun EmptyView(message: String = "Nothing in here Yet!, Please comeback later"){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.List,
            contentDescription = "Empty",
            tint = MaterialTheme.colors.error,
            modifier = Modifier.size(108.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        EmptyText(message)
    }
}

@Composable
fun ErrorText(text: String, modifier: Modifier = Modifier){
    Text(text = text, modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 48.dp),
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center, color = MaterialTheme.colors.error.copy(alpha = 0.9F))
}

@Composable
fun EmptyText(text: String, modifier: Modifier = Modifier){
    Text(text = text, modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 48.dp),
        style = MaterialTheme.typography.body2,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center)
}