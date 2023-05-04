package com.example.pocket_rpg.characters.view.create_new_character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocket_rpg.characters.Create
import com.example.pocket_rpg.characters.model.RpgCharacter
import com.example.pocket_rpg.characters.view.character_preview.CharacterDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NewCharacterViewModel
@Inject
constructor(
    private val create: Create,
    private val savedStateHandle: SavedStateHandle //
): ViewModel() {
    private val _state = MutableStateFlow(NewCharacterState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NewCharacterCallback>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: NewCharacterEvent) {
        when(event){
            is NewCharacterEvent.ChangedName -> _state.value = _state.value.copy(name = event.name)
            is NewCharacterEvent.ChangeBackStory -> _state.value = _state.value.copy(backstory = event.backstory)
            is NewCharacterEvent.Save -> {
                viewModelScope.launch {
                    create(
                        RpgCharacter(
                            UUID.randomUUID().toString(),
                            _state.value.name,
                            1,
                            2,
                            3,
                            0,
                            true,
                            listOf()
                        )
                    )
                    _state.value = _state.value.copy("", 0,0,0, "")
                    _eventFlow.emit(NewCharacterCallback.Saved)
                }
            }
        }
    }
}

data class NewCharacterState(
    val name: String = "",
    val body: Int = 0,
    val mind: Int = 0,
    val soul: Int = 0,
    val backstory: String = ""
)

typealias EventBus = (NewCharacterEvent) -> Unit
sealed class NewCharacterEvent{
    object Save: NewCharacterEvent()
    class ChangedName(val name: String): NewCharacterEvent()
    class ChangeBackStory(val backstory: String) : NewCharacterEvent()
}

sealed class NewCharacterCallback{
    object Saved: NewCharacterCallback()
}