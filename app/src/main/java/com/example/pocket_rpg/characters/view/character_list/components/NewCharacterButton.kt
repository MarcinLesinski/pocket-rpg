package com.example.pocket_rpg.characters.view.character_list.components

import android.widget.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pocket_rpg.presentation.Route

@Composable
fun NewCharacterButton(nav: NavController?) {
    FloatingActionButton(
        onClick = { nav?.navigate(Route.CharacterCreateNew()) },
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "New character"
        )
    }
}