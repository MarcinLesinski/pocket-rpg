package com.example.pocket_rpg.characters.model

data class CharacterVisitingCard(
    val id: String,
    val name: String,
    val isAlive: Boolean,
    val hp: Long,
    val dmg: Long,
)
