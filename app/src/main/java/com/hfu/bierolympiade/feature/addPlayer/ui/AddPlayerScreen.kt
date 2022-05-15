package com.hfu.bierolympiade.feature.addPlayer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R

@Composable
fun AddPlayerScreen(viewModel: AddPlayerViewModel = viewModel()) {
    AddPlayerScreenUi(viewModel::onAddPlayer)
}

@Composable
fun AddPlayerScreenUi(onAddPlayer: (name: String, music: String, description: String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var music by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
                        Color(0xFFF2C299)
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
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .background(
                    Color(0xFFF2C299)
                )
        ) {

        }
        Button(
            onClick = { onAddPlayer(name, music, description) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFEC814C),
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
    AddPlayerScreenUi({ _, _, _ -> })
}