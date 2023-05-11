package com.example.pocket_rpg.characters.view.create_new_character

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feats.Feat
import com.example.pocket_rpg._common.ui.components.CenteredColumn
import com.example.pocket_rpg._common.ui.components.Listen
import com.example.pocket_rpg.characters.view.character_preview.FeatTag
import com.example.pocket_rpg.characters.view.create_new_character.components.SaveButton
import com.example.pocket_rpg.characters.view.create_new_character.components.feats
import com.example.pocket_rpg.presentation.Route
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.math.*

@Composable
fun NewCharacterScreen(
    navController: NavController? = null,
    viewModel: NewCharacterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val events = viewModel::onEvent
    val commands = viewModel.eventFlow

    ScreenContent(state, events, commands, navController)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ScreenContent(
    state: NewCharacterState,
    send: EventBus,
    commands: SharedFlow<NewCharacterCallback>,
    nav: NavController? = null,
) {
    val onSave = { nav?.navigate(Route.Characters()) }

    Listen(commands) {
        when (it) {
            NewCharacterCallback.Saved -> onSave()
        }
    }
//    var body by remember { mutableStateOf(1) }
//    var mind by remember { mutableStateOf(1) }
//    var soul by remember { mutableStateOf(1) }
//        var name by remember { mutableStateOf(TextFieldValue("")) }
//        var body by remember { mutableStateOf(NumberF(0)) }
//        var mind by remember { mutableStateOf(TextFieldValue(0)) }
//        var soul by remember { mutableStateOf(TextFieldValue(0)) }
//        var backstory by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        floatingActionButton = { SaveButton(send) }
    ) {

        CenteredColumn {
            TextField(
                value = state.name,
                onValueChange = { send(NewCharacterEvent.ChangedName(it)) },
                label = { Text("Name") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go
                ),
            )

            Row {
                Text("Body: " + state.body.toString())
                Spacer(Modifier.width(50.dp))
                Text("Mind: " + state.mind.toString())
                Spacer(Modifier.width(50.dp))
                Text("Soul: " + state.soul.toString())

            }
            Column(modifier = Modifier.padding(horizontal = 100.dp)) {
                val freePoints = 7 - (state.body + state.mind + state.soul)
                if (freePoints > 0)
                    Text("points left: $freePoints")
                else Text("")


                val body = slide(state.body, state.isAddingStatsBlocked, Color.Red)
                send(NewCharacterEvent.StatsChanged(body = body))

                val mind = slide(state.mind, state.isAddingStatsBlocked, Color.Blue)
                send(NewCharacterEvent.StatsChanged(mind = mind))

                val soul = slide(state.soul, state.isAddingStatsBlocked, Color.Yellow)
                send(NewCharacterEvent.StatsChanged(soul = soul))


            }

            TextField(
                value = state.backstory,
                onValueChange = { send(NewCharacterEvent.ChangeBackStory(it)) },
                label = { Text("Backstory") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go
                )
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Text("Feats")
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add feat",
                        modifier = Modifier.clickable { send(NewCharacterEvent.ToggleFeats) }
                    )
                }
                if (state.feats.isNotEmpty())
                    Feats(state.feats)
                else
                    FeatTag(" add feats ")
                
                AnimatedVisibility(
                    visible = state.isFeatListVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    val selectedFeats = feats(state.allFeats)
                    send(NewCharacterEvent.SelectedFeats(selectedFeats))
                }
            }
        }
    }
}

@Composable
fun Feats(feats: List<Feat>) {
    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        feats.forEach { tag -> FeatTag(tag = tag.name) }
    }
}

@Preview
@Composable
fun Preview() {
    ScreenContent(
        state = NewCharacterState(
            "Ashar", 1, 3, 2, 7,"Sarcastic master of illusion from oriental nomadic tribe.", listOf(), listOf()
        ),
        send = {},
        MutableSharedFlow()
    )
}

@Composable
fun slide(value: Int, blocked: Boolean, color: Color): Int {
    var state by remember { mutableStateOf(value.toFloat()) }
    val inactiveColor = if(blocked) Transparent else color
    Slider(
        value = state,
        valueRange = 0f..5f,
        steps = 4,
        onValueChange = {
            state = it
        },
//        onValueChangeFinished = {
//            val v = min((initialValue + freePoints).toFloat(), state)
//                state = v.roundToInt().toFloat()
//        },
        colors = SliderDefaults.colors(
            activeTrackColor = color,
            inactiveTrackColor = inactiveColor,
            thumbColor = color,
            disabledActiveTickColor = Transparent,
            activeTickColor = Transparent,
            inactiveTickColor = Transparent,
            disabledInactiveTickColor = Transparent
        )
    )
    return state.toInt()
}
