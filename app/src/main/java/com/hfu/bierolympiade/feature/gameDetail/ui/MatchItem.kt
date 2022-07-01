package com.hfu.bierolympiade.feature.gameDetail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfu.bierolympiade.domain.model.MatchId
import com.hfu.bierolympiade.domain.model.TeamId

@Composable
fun MatchItem(
    match: MatchUI,
    index: Int,
    updateMatchScoreValue: (teamId: TeamId, value: Int) -> Unit
) {
    var score1 by remember { mutableStateOf(match.score1.toString()) }
    var score2 by remember { mutableStateOf(match.score2.toString()) }

    if (match.score1 == null && score1 != null) {
        score1 = match.score1.toString()
    }
    if (match.score2 == null && score2 != null) {
        score2 = match.score2.toString()
    }

    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(8.dp)
            .height(140.dp),
    ) {
        if(!match.isHighScoreGame) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Match $index",
                    fontSize = 20.sp,
                )
                Text(
                    text = "Win: ${match.winCondition}",
                    fontSize = 15.sp
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                match.playerNamesTeam1.map {
                    Text(
                        text = it,
                        modifier = Modifier.width(100.dp),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            OutlinedTextField(
                value = score1, onValueChange = {
                    score1 = it
                    if (it.isNotEmpty()) updateMatchScoreValue(match.team1, it.toInt())
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.widthIn(
                    1.dp,
                    55.dp
                )
            )
            if(!match.isHighScoreGame) {
                Column {
                    match.playerNamesTeam2.map {
                        Text(text = it, Modifier.width(100.dp), overflow = TextOverflow.Ellipsis)
                    }
                }

                OutlinedTextField(
                    value = score2, onValueChange = {
                        score2 = it
                        if (it.isNotEmpty()) updateMatchScoreValue(match.team2, it.toInt())
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.widthIn(
                        1.dp,
                        55.dp
                    )
                )
            }
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
            winCondition = 0,
            playerNamesTeam1 = emptyList(),
            playerNamesTeam2 = emptyList(),
            team1 = TeamId("foo"),
            team2 = TeamId("foo"),
            score1 = 0,
            score2 = 0,
            isHighScoreGame = false
        ),
        0
    ) { _, _ -> }
}