package com.example.pocket_rpg._common.di

import com.example.pocket_rpg._common.Constants.CHARS_API_URL
import com.example.pocket_rpg._common.services.characters.CharactersApi
import com.example.pocket_rpg._common.services.characters.CharactersRepositoryImpl
import com.example.pocket_rpg.characters.ports.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCharacterApi(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(CHARS_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideCharacterRepository(api: CharactersApi): CharacterRepository {
//        return CharactersRepositoryImpl()
//    }


}