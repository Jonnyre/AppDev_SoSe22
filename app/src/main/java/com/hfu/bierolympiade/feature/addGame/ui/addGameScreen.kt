package com.hfu.bierolympiade.feature.addGame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsDarkOrange
import com.hfu.bierolympiade.ui.theme.RsLightOrange
import com.hfu.bierolympiade.ui.theme.RsWhite

@Composable
fun AddGameScreen(viewModel: AddGameViewModel = viewModel()) {
    val addGameUi by viewModel.bindUi(LocalContext.current).observeAsState()
    addGameUi?.let { AddGameScreenUi(it, viewModel::onSaveGame) }
}

@Composable
fun AddGameScreenUi(addGameUI: AddGameUI, onSaveGame: (teamSize: Int, winCondition: Int, points: Int, rules: String) -> Unit) {
    var teamSize by remember { mutableStateOf(1) }
    var winCondition by remember { mutableStateOf(0) }
    var points by remember { mutableStateOf(0) }
    var rules: String? by remember { mutableStateOf(addGameUI.rules) }

    if (rules == null && addGameUI.rules != null) {
        rules = addGameUI.rules;
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 0.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        RsLightOrange
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_users),
                    contentDescription = "User",
                    modifier = Modifier.size(80.dp)
                )
            }
        }
        if(!addGameUI.isHighScore) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Teamsize", fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                plusMinusCounter(
                    teamSize,
                    { teamSize += 1 },
                    { if (teamSize > 1) teamSize -= 1 else return@plusMinusCounter })
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Win Condition", fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                plusMinusCounter(
                    winCondition,
                    { winCondition += 1 },
                    { if (winCondition > 1) winCondition -= 1 else return@plusMinusCounter })
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Points For Winner", fontSize = 20.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            plusMinusCounter(
                points,
                { points += 1 },
                { if (points > 1) points -= 1 else return@plusMinusCounter })
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Rules", fontSize = 20.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = rules ?: "",
                onValueChange = { rules = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = {
                rules?.let { onSaveGame(teamSize, winCondition, points, it) }
                navControllerGlobal?.navigate("addEvent?eventId=${addGameUI.eventId.value}")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RsDarkOrange,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .height(75.dp)
        ) {
            Text(text = "Save")
        }

    }
}

@Preview
@Composable
fun AddGameScreen_Preview() {
    AddGameScreenUi(AddGameUI(EventId("foo"), "test", true)) { _, _, _, _ -> }
}

@Composable
fun plusMinusCounter(teamSize: Int, onPlus: () -> Unit, onMinus: () -> Unit) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Button(
            onClick = onMinus,
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = RsLightOrange)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_minus),
                contentDescription = "Minus Button",
                tint = RsWhite
            )
        }
    }
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(text = teamSize.toString(), fontSize = 25.sp)
    }
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Button(
            onClick = onPlus,
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RsLightOrange,
                contentColor = RsDarkOrange
            )
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                contentDescription = "Plus Button",
                tint = RsWhite
            )
        }
    }
}