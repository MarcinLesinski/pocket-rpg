package com.example.pocket_rpg.game.view.create_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.pocket_rpg.presentation.Route
import com.example.pocket_rpg._common.ui.components.*
@Composable
fun CreateGameScreen(
    nav: NavController?
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    CenteredColumn {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go
            )
        )

        Button(onClick = { nav?.navigate(Route.MasterScreen())}) {
            Text("Create Game")
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    CreateGameScreen(nav = null)
}