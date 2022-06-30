package com.hfu.bierolympiade.feature.addPlayer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsDarkOrange
import com.hfu.bierolympiade.ui.theme.RsLightOrange

@Composable
fun AddPlayerScreen(viewModel: AddPlayerViewModel = viewModel()) {
    val player by viewModel.bindUi(LocalContext.current).observeAsState()
    AddPlayerScreenUi(player, viewModel::onSavePlayer)
}

@Composable
fun AddPlayerScreenUi(player: AddPlayerUI?, onSavePlayer: (name: String, music: String, description: String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var music by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    if(player != null && name == "") {
        name = player.name
        music = player.music
        description = player.description
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(25.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = name, fontSize = 20.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = music,
                onValueChange = { music = it },
                label = { Text("Music") })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") })
        }


        Text(text = "Stats", fontSize = 20.sp)
        Text(text = "Wins" + player?.winningMatches)
        Text(text = "Loses" + player?.losingMatches)
        Button(
            onClick = {
                onSavePlayer(name, music, description)
                navControllerGlobal?.popBackStack()
                navControllerGlobal?.navigate("players")
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
fun AddPlayerScreen_Preview() {
    AddPlayerScreenUi(AddPlayerUI(
        null, "Test", "TestMusik", "TestDescr", 0, 0
    )) { _, _, _ -> }
}