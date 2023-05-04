package com.example.pocket_rpg._common.services.characters

import com.example.pocket_rpg._common.services.characters.dto.Character
import com.example.pocket_rpg._common.services.characters.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {
    @GET("/v1/coins")
    suspend fun all(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun one(@Path("coinId") coinId: String): Character
}