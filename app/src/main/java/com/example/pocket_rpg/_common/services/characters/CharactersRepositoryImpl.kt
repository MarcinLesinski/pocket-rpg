package com.example.pocket_rpg._common.services.characters

import com.example.pocket_rpg.characters.ports.CharacterRepository
import com.example.pocket_rpg.characters.model.CharacterVisitingCard
import com.example.pocket_rpg.characters.model.Feat
import com.example.pocket_rpg.characters.model.RpgCharacter
import javax.inject.Inject

class CharactersRepositoryImpl
@Inject
constructor() : CharacterRepository {
    private val feats = listOf(
        Feat("Sneak attack", 1),
        Feat("Hide", 3),
        Feat("Mage hand", 5)
    )
    val characters = mutableMapOf(
        "1" to RpgCharacter("1", "Bruno", 500, 400, 150, 400, true, feats),
        "2" to RpgCharacter("2", "Agatha", 400, 400, 120, 140, true, feats),
        "3" to RpgCharacter("3", "Vallo", 300, 100, 100, 230, true, feats),
        "4" to RpgCharacter("4", "Kama", 200, 1500, 80, 440, false, feats),
        "5" to RpgCharacter("5", "Nathan", 800, 800, 45, 143, true, feats),
    )


    override suspend fun all(): List<CharacterVisitingCard> = characters.values.map{ character ->
        CharacterVisitingCard(character.id, character.name, character.isAlive, character.maxHp, character.dmg)
    }

    override suspend fun one(character: String): RpgCharacter =
        characters[character] ?: error("key not found")

    override suspend fun delete(character: RpgCharacter) {
        characters.remove(character.id)
    }

    override suspend fun insert(character: RpgCharacter) {
        characters[character.id] = character
    }
}