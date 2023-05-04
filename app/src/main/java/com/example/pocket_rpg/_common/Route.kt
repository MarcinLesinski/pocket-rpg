package com.example.pocket_rpg.presentation

import androidx.lifecycle.SavedStateHandle

sealed class Route(protected val baseRoute: String) {
    protected open val params: List<String> = emptyList()
    operator fun invoke(): String = baseRoute + params.joinToString { "/{$it}" }
    open operator fun invoke(savedStateHandle: SavedStateHandle): List<Argument> = emptyList()


    object MainMenu : Route("main_menu_screen")
    object Characters : Route("character_list_screen")
    object CharacterPreview : Route("character_detail_screen") {
        override val params: List<String> = listOf("charId")
        operator fun invoke(characterId: String): String = "$baseRoute/$characterId"
        override fun invoke(savedStateHandle: SavedStateHandle): List<Argument> {
            return params.map { argument(savedStateHandle, it) }
        }
    }
    object CharacterLevelUp : Route("character_edit_screen") {
        override val params: List<String> = listOf("charId")
        operator fun invoke(characterId: String): String = "$baseRoute/$characterId"
        override fun invoke(savedStateHandle: SavedStateHandle): List<Argument> {
            return params.map { argument(savedStateHandle, it) }
        }
    }
    object CharacterCreateNew: Route("character_create_new")
    object Campaigns: Route("campaigns_screen")
    object CreateGame: Route("create_game_screen")
    object MasterScreen: Route("master_screen")
    object PlayerScreen: Route("player_screen")
}


fun argument(state: SavedStateHandle, param: String): Argument {
    return object : Argument {
        override fun <T> get(): T = state.get<T>(param) ?: error("")
    }
}

interface Argument {
    fun <T> get(): T
}