package com.example.pocket_rpg.characters.view.character_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocket_rpg._common.utils.Resource
import com.example.pocket_rpg.characters.Create
import com.example.pocket_rpg.characters.Delete
import com.example.pocket_rpg.characters.GetAll
import com.example.pocket_rpg.characters.model.RpgCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel
@Inject
constructor(
    private val getAllCharacters: GetAll,
    private val createCharacter: Create,
    private val deleteCharacter: Delete
): ViewModel() {
    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()

    private var recentlyDeletedCharacter: RpgCharacter? = null
    init{
        getCharacters()
    }

    private fun getCharacters() {
        getAllCharacters().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CharacterListState(characters = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = CharacterListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CharacterListState(error = result.message ?: "Unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun delete(character: RpgCharacter) {
        viewModelScope.launch {
            deleteCharacter(character)
            recentlyDeletedCharacter = character
        }

    }

    fun create(character: RpgCharacter) {
        viewModelScope.launch {
            createCharacter(character)
        }
    }

    fun restore(){
        viewModelScope.launch {
            createCharacter(recentlyDeletedCharacter!!)
            recentlyDeletedCharacter = null
        }
    }
}