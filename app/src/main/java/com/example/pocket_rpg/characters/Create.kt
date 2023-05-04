package com.example.pocket_rpg.characters

import com.example.pocket_rpg.characters.model.RpgCharacter
import com.example.pocket_rpg.characters.ports.CharacterRepository
import javax.inject.Inject

class Create
@Inject
constructor(
    private val charactersRepository: CharacterRepository
) {
    suspend operator fun invoke(character: RpgCharacter) {

        charactersRepository.insert(character)
    }
}