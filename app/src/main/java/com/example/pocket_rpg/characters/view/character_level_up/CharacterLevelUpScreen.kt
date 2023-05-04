package com.example.pocket_rpg.characters.view.character_level_up

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pocket_rpg.characters.view.character_preview.CharacterDetailState

@Composable
fun CharacterLevelUpScreen(
    nav: NavController,
    viewModel: CharacterLevelUpViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    CharacterLevelUpContent(state.value, nav)
}

@Composable
private fun CharacterLevelUpContent(
    state: CharacterDetailState,
    nav: NavController?,
) {
    state.character?.let{ character ->
        Box(){
            Column() {
                Text(text = "level up")
                Text(text = character.name)
            }
        }
    }

}