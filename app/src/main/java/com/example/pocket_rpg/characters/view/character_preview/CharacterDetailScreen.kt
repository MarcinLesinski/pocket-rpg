package com.example.pocket_rpg.characters.view.character_preview


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pocket_rpg.characters.model.Feat
import com.example.pocket_rpg.characters.model.RpgCharacter
import com.example.pocket_rpg.presentation.Route
import com.example.pocket_rpg._common.ui.theme.PocketrpgTheme
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun CharacterDetailScreen(
    navController: NavController? = null,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
) {
//    val state = viewModel.state.collectAsState()
    val state by viewModel.state.collectAsState()
    ScreenContent(state, navController)
}

@Composable
private fun ScreenContent(
    state: CharacterDetailState,
    nav: NavController?,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        state.character?.let { character ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Name(character)
                        Level(character)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    IsAlive(character)
                    Feats(character)
                    Row() {
                        Button(onClick = {
                            nav?.navigate(Route.CharacterLevelUp(character.id))
                        }
                        ) {
                            Text("level up")
                        }
                        Button(onClick = {
                            nav?.navigate(Route.PlayerScreen()) }
                        ) {
                            Text("Join game")
                        }
                    }
                }
//                items(character.feats) { feat ->
//                    Text(text = feat.name)
//                }
            }
        }
        if (state.error.isNotBlank()) {
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

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun Feats(character: RpgCharacter) {
    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        character.feats.forEach { tag -> CharacterTag(tag = tag.name) }
    }
}

@Composable
private fun IsAlive(character: RpgCharacter) {
    if (character.isAlive) return

    Text(
        text = "Character died",
        color = Red,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.End,
        modifier = Modifier
//            .align(CenterVertically)
//            .weight(2f)
    )
}

@Composable
private fun RowScope.Name(character: RpgCharacter) {
    Text(
        text = "${character.name}",
        style = MaterialTheme.typography.h3,
        modifier = Modifier.weight(8f)
    )
}

@Composable
private fun RowScope.Level(character: RpgCharacter) {
    Text(
        text = "(${character.id})",
        style = MaterialTheme.typography.h3,
//        modifier = Modifier.weight(8f)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val state = CharacterDetailState(
        character = RpgCharacter(
            "1",
            "Amicia dau Thores",
            100,
            50,
            140,
            1000,
            false,
            listOf(
                Feat("Sneak attack", 1),
                Feat("Hide", 3),
                Feat("Mage hand", 5)
            )
        )
    )
    PocketrpgTheme {
        ScreenContent(state, null)
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    val state = CharacterDetailState(error = "błunt")
    PocketrpgTheme {
        ScreenContent(state, null)
    }
}


