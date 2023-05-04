package com.example.pocket_rpg._common.main_menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pocket_rpg.presentation.Route

@Composable
fun MainMenuScreen(
    nav: NavController? = null
) {
    Box(){
        Column {
            Title()
            Characters(nav)
            Play(nav)
        }
    }
}

@Composable
private fun Title() {
    Text("Pocket rpg", modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),)
}

@Composable
private fun Characters(nav: NavController?) {
    Text("Characters",
        modifier = Modifier
            .fillMaxWidth()
            .clickable { nav?.navigate(Route.Characters()) }
            .padding(20.dp),
    )
}

@Composable
fun Play(nav: NavController?) {
    Text("Play", modifier = Modifier
        .fillMaxWidth()
        .clickable { nav?.navigate(Route.CreateGame()) }
        .padding(20.dp),
    )
}

@Preview
@Composable
fun Preview() {
    MainMenuScreen()
}