package com.example.pocket_rpg.characters.view.character_preview

import com.example.pocket_rpg.characters.model.RpgCharacter

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: RpgCharacter? = null,
    val error: String = ""
) {
}