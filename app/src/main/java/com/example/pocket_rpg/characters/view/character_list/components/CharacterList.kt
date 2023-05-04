package com.example.pocket_rpg.characters.view.character_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.pocket_rpg.characters.model.CharacterVisitingCard
import com.example.pocket_rpg.characters.model.Coin
import com.example.pocket_rpg.characters.model.RpgCharacter

@Composable
fun CharacterListItem(
    character: CharacterVisitingCard,
    onItemClick: (CharacterVisitingCard) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(character) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
//            text = "${character.rank}. ${character.name} (${character.symbol})",
            text = "${character.name}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = if(character.isAlive) "live" else "dead",
            color = if(character.isAlive) Green else Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(CenterVertically)
        )
    }
}

