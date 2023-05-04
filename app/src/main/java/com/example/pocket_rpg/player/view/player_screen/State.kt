package com.example.pocket_rpg.player.view.player_screen



data class State(
    val isLoading: Boolean = false,
    val characters: Int = 1,
    val error: String = ""
)