package com.example.pocket_rpg.characters

import com.example.pocket_rpg._common.utils.Resource
import com.example.pocket_rpg.characters.model.CharacterVisitingCard
import com.example.pocket_rpg.characters.ports.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAll
@Inject
constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(): Flow<Resource<List<CharacterVisitingCard>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.all().map { it }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error http"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Error IO"))
        }
    }
}