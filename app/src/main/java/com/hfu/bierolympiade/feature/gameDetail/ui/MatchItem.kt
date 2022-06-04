package com.hfu.bierolympiade.feature.gameDetail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfu.bierolympiade.domain.model.MatchId

@Composable
fun MatchItem(match: MatchUI, index: Int) {
    var score1 by remember { mutableStateOf("") }
    var score2 by remember { mutableStateOf("") }
    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(8.dp).
        height(140.dp),
    ) {
        Row(modifier = Modifier.padding(bottom = 25.dp)) {
            Text(
                text = "Match $index",
                fontSize = 20.sp
            )
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                match.playerNamesTeam1.map {
                    Text(text = it)
                }
            }

            OutlinedTextField(
                value = score1, onValueChange = {score1 = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.widthIn(
                    1.dp,
                    55.dp
                )
            )
            Column() {
                match.playerNamesTeam2.map {
                    Text(text = it)
                }
            }

            OutlinedTextField(
                value = score2, onValueChange = {score2 = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.widthIn(
                    1.dp,
                    55.dp
                )
            )
        }
    }
}

@Preview
@Composable
fun MatchItem_Preview() {
    MatchItem(
        MatchUI(
            MatchId("foo"),
            type = 0,
            state = 0,
            playerNamesTeam1 = emptyList(),
            playerNamesTeam2 = emptyList()
        ),
        0
    )
}