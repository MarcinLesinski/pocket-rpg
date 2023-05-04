package com.example.pocket_rpg.characters

import com.example.pocket_rpg.characters.model.RpgCharacter
import com.example.pocket_rpg.characters.ports.CharacterRepository
import javax.inject.Inject

class Delete
    @Inject
    constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(character: RpgCharacter) {
        repository.delete(character)
    }
}