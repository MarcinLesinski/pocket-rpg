package com.example.pocket_rpg.characters.view.create_new_character

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pocket_rpg._common.ui.components.CenteredColumn
import com.example.pocket_rpg.characters.view.create_new_character.components.SaveButton
import com.example.pocket_rpg.presentation.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEmpty

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

@Composable
private fun ScreenContent(
    state: NewCharacterState,
    events: EventBus,
    commands: SharedFlow<NewCharacterCallback>,
    nav: NavController? = null,
) {
    val onSave = { nav?.navigate(Route.Characters()) }

    LaunchedEffect(true /* always execute? */) {
        commands.collectLatest { command ->
            when (command) {
                NewCharacterCallback.Saved -> onSave()
            }
        }
    }

    Scaffold(
        floatingActionButton = { SaveButton(events) }
    ) {
//        var name by remember { mutableStateOf(TextFieldValue("")) }
//        var body by remember { mutableStateOf(NumberF(0)) }
//        var mind by remember { mutableStateOf(TextFieldValue(0)) }
//        var soul by remember { mutableStateOf(TextFieldValue(0)) }
//        var backstory by remember { mutableStateOf(TextFieldValue("")) }
        CenteredColumn() {
            TextField(
                value = state.name,
                onValueChange = { events(NewCharacterEvent.ChangedName(it)) },
                label = { Text("Name") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go
                ),
            )

            TextField(
                value = state.backstory,
                onValueChange = { events(NewCharacterEvent.ChangeBackStory(it)) },
                label = { Text("Backstory") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go
                )
            )
        }
    }

}

//@Composable
//fun
//
////Utils
//fun <T>SharedFlow<T>.execute(block: (T) -> Unit) {
//    LaunchedEffect(true /* always execute? */) {
//        commands.collectLatest { command ->
//            when (command) {
//                NewCharacterCallback.Saved -> onSave()
//            }
//        }
//    }
//}


@Preview
@Composable
fun Preview() {
    ScreenContent(
        state = NewCharacterState(
            "Ashar", 1, 3, 2, "Sarcastic master of illusion from oriental nomadic tribe."
        ),
        events = {},
        MutableSharedFlow()
    )
}