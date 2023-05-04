package com.example.pocket_rpg.player.view.player_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pocket_rpg.characters.view.character_list.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel
@Inject
constructor(

): ViewModel() {
    private val _state = mutableStateOf(CharacterListState())
    val state: State<CharacterListState> = _state

}