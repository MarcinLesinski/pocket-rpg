package com.example.pocket_rpg.characters.view.create_new_character.components


import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save

import androidx.compose.runtime.Composable
import com.example.pocket_rpg.characters.view.create_new_character.EventBus
import com.example.pocket_rpg.characters.view.create_new_character.NewCharacterEvent

@Composable
fun SaveButton(eventBus: EventBus) {
    FloatingActionButton(
        onClick = { eventBus(NewCharacterEvent.Save) }
    ) {
        Icon(imageVector = Icons.Default.Save, contentDescription = "Create character" )
    }
}