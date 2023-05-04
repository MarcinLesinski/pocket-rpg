package com.example.pocket_rpg.characters.view.character_preview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocket_rpg._common.utils.Resource
import com.example.pocket_rpg.characters.GetOne
import com.example.pocket_rpg.presentation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel
@Inject
constructor(
    private val getCharacterUseCase: GetOne,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state.asStateFlow()

    init {
        val (charId) = Route.CharacterPreview(savedStateHandle)
        getCharacter(charId.get())
    }

    private fun getCharacter(characterId: String) {
        getCharacterUseCase(characterId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterDetailState(character = result.data)
                }
                is Resource.Loading -> {
                    _state.value = CharacterDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CharacterDetailState(error = result.message ?: "Unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }
}

