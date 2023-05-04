package com.example.pocket_rpg.characters.ports

import com.example.pocket_rpg.characters.model.RpgCharacter
import com.example.pocket_rpg.characters.model.CharacterVisitingCard

interface CharacterRepository {
    suspend fun all(): List<CharacterVisitingCard>

    suspend fun  one(character: String): RpgCharacter

    suspend fun delete(character: RpgCharacter)

    suspend fun insert(character: RpgCharacter)
}