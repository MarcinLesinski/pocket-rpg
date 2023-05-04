package com.example.pocket_rpg.characters.model


data class RpgCharacter(
    val id: String,
    val name: String,
    val maxHp: Long,
    val currentHp: Long,
    val dmg: Long,
    val money: Long,
    val isAlive: Boolean,
    val feats: List<Feat>
)
