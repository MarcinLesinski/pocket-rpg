package com.example.pocket_rpg.characters.view.character_list

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pocket_rpg.characters.model.CharacterVisitingCard
import com.example.pocket_rpg.characters.view.character_list.components.NewCharacterButton
import com.example.pocket_rpg.characters.view.character_preview.CharacterDetailState
import com.example.pocket_rpg.presentation.Route

@Composable
fun CharacterListScreen(
    navController: NavController? = null,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ScreenContent(navController, state)
}

@Composable
fun ScreenContent(
    nav: NavController?,
    state: CharacterListState,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = { NewCharacterButton(nav) },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.characters) { character ->
                CharacterListItem(
                    character = character,
                    onItemClick = {
                        nav?.navigate(Route.CharacterPreview(character.id))
                    }
                )

            }
        }
    }

    if (state.error.isNotBlank()) {
        Box {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }

    if (state.isLoading) {
        Box {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


@Preview
@Composable
fun Preview() {
    val state =
        CharacterListState(characters = listOf(CharacterVisitingCard("1", "name", true, 500, 120)))
    ScreenContent(null, state)
}