package com.example.pocket_rpg._common.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <T> Listen(flow: SharedFlow<T>, block: (T)->Unit){
    LaunchedEffect(true) { flow.collectLatest(block) }
}
