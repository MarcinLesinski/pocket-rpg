package com.example.pocket_rpg._common.services.characters.dto

import com.example.pocket_rpg.characters.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin = Coin(
    id = id,
    isActive = isActive,
    name = name,
    isNew = isNew,
    rank = rank,
    symbol = symbol,
    type = type
)
