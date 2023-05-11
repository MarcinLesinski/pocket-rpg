package com.example.pocket_rpg.characters.view.create_new_character.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.unit.dp
import com.example.feats.Feat
import com.example.pocket_rpg.characters.view.character_preview.FeatTag
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun feats(allFeats: List<Feat>): List<Feat> {
    var items by remember{ mutableStateOf( allFeats.map{ Selectable(it) }  ) }

    LazyColumn() {
        items(items.size){ i ->
            Row(
                modifier = Modifier.clickable {
                    items = items.mapIndexed{index, item ->
                        if (index == i) item.toggled else item
                    }
            },
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                val item = items[i]
//                FeatTag(item.data.name)
                Text(item.data.name)
                if (item.selected){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Green,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }

    return items.filter{it.selected}.map{it.data}
}

data class Selectable<T>(
    val data: T,
    val selected: Boolean = false
) {
    val toggled get() = copy(selected = this.selected.not())
}