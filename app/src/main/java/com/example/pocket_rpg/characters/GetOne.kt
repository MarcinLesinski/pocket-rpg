package com.example.pocket_rpg.characters

import com.example.pocket_rpg._common.utils.Resource
import com.example.pocket_rpg.characters.model.RpgCharacter
import com.example.pocket_rpg.characters.ports.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetOne
@Inject
constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<RpgCharacter>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.one(coinId)
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error http"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Error IO"))
        }
    }
}
