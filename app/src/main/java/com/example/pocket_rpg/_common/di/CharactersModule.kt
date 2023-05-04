package com.example.pocket_rpg._common.di

import com.example.pocket_rpg._common.services.characters.CharactersRepositoryImpl
import com.example.pocket_rpg.characters.ports.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    @Provides
    @Singleton
    fun providesRepository():CharacterRepository {
        return CharactersRepositoryImpl()
    }
}