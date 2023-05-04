package com.example.pocket_rpg.tests_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Screen(
    vm: TestViewModel = hiltViewModel()
) {
    val n1 by vm.number.collectAsState()
    val n2 = vm.number2
    val state =

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { vm.inc() }){
        Column {
            Text("state flow: " + n1.toString())
            Text("state" + n2.toString())
        }
    }
}