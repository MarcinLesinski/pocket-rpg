package com.example.pocket_rpg.characters.view.character_list

import com.example.pocket_rpg.characters.model.CharacterVisitingCard
import com.example.pocket_rpg.characters.model.Coin

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<CharacterVisitingCard> = emptyList(),
    val error: String = ""
)