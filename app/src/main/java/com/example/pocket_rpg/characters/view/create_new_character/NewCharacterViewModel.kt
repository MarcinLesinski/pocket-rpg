package com.example.pocket_rpg.characters.view.create_new_character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feats.Feat
import com.example.feats.Feats
import com.example.pocket_rpg.characters.Create
import com.example.pocket_rpg.characters.model.RpgCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Integer.min
import java.util.UUID
import javax.inject.Inject

interface StateT {
    val statet: StateFlow<NewCharacterState>
    fun event(i: Int)
    fun onEventt()
}

@HiltViewModel
class NewCharacterViewModel
@Inject
constructor(
    private val create: Create,
    private val savedStateHandle: SavedStateHandle
): ViewModel(), StateT {
    private val _state =  MutableStateFlow(NewCharacterState(allFeats = Feats.all))//
    val state = _state.asStateFlow()
    override val statet = _state.asStateFlow()
    override fun event(i: Int){}
    override fun onEventt(){}
//    val state2 = savedStateHandle.getStateFlow("state", NewCharacterState())


    private val _eventFlow = MutableSharedFlow<NewCharacterCallback>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: NewCharacterEvent) {
        when(event){
            is NewCharacterEvent.ChangedName -> _state.value = _state.value.copy(name = event.name)
            is NewCharacterEvent.ChangeBackStory -> _state.value = _state.value.copy(backstory = event.backstory)
            is NewCharacterEvent.Save -> {
                viewModelScope.launch {
                    //
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
//                    Compose - bilbioteka - acompaniaist - permisiony
//                    firebas
                    _state.update { it.copy(body = 6) }
                    _state.value = _state.value.copy("", 0,0,0, 7,"")
                    _eventFlow.tryEmit(NewCharacterCallback.Saved)
                    _eventFlow.emit(NewCharacterCallback.Saved)
                }
            }
            is NewCharacterEvent.StatsChanged -> _state.update {
                var state = when{
                    event.body != null -> it.copy(body = ceilStat(event.body, state.value.mind + state.value.soul, 7 ))
                    event.mind != null -> it.copy(mind = ceilStat(event.mind, state.value.body + state.value.soul, 7 ))
                    event.soul != null -> it.copy(soul = ceilStat(event.soul, state.value.mind + state.value.body, 7 ))
                    else -> it
                }
                state = state.copy(
                    isAddingStatsBlocked = 7 <= state.body + state.mind + state.soul,
                    freePoints = 7 - state.body + state.mind + state.soul
                )
                state
            }
            is NewCharacterEvent.SelectedFeats -> {
              _state.update{
                  _state.value.copy( feats = event.selectedFeats )
              }
            }
            is NewCharacterEvent.ToggleFeats -> {
                _state.update{
                    it.copy(isFeatListVisible = it.isFeatListVisible.not())
                }
            }
        }
    }
}

private fun ceilStat(stat: Int, rest:Int, ceil: Int): Int {
    val freePoints = ceil - rest;
     return min(freePoints, stat)
}

data class NewCharacterState(
    val name: String = "",
    val body: Int = 1,
    val mind: Int = 1,
    val soul: Int = 1,
    val freePoints: Int = 4,
    val backstory: String = "",
    val feats: List<Feat> = listOf(),
    val allFeats: List<Feat>,
    val isFeatListVisible: Boolean = false,
    val isAddingStatsBlocked: Boolean = false
) {

}

typealias EventBus = (NewCharacterEvent) -> Unit
sealed class NewCharacterEvent {
    object Save: NewCharacterEvent()
    class ChangedName(val name: String): NewCharacterEvent()
    class ChangeBackStory(val backstory: String) : NewCharacterEvent()
    class StatsChanged(val body: Int? = null, val mind: Int? = null, val soul: Int? = null) : NewCharacterEvent()
    class SelectedFeats(val selectedFeats: List<Feat>) : NewCharacterEvent()
    object ToggleFeats: NewCharacterEvent()
}

sealed class NewCharacterCallback {
    object Saved: NewCharacterCallback()
}
