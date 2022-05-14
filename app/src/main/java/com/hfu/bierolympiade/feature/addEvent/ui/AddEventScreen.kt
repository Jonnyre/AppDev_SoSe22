package com.hfu.bierolympiade.feature.addEvent.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import com.hfu.bierolympiade.feature.event.ui.EventUI
import com.hfu.bierolympiade.feature.event.ui.EventViewModel

@Composable
fun AddEventScreen(viewModel: AddEventViewModel = viewModel()) {
    var eventname by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var fee by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(25.dp)
            .background(Color(0xFFFAFCFE)),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
        ) {
            Text(text = "Add new Event", fontSize = 20.sp)
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(text = "Discard")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                        painter = painterResource(R.drawable.ic_blank_event),
                        contentDescription = "Event",
                        modifier = Modifier.size(80.dp)
                    )
                }
                Text(text = "Choose Image")
            }

            TextField(
                value = eventname,
                onValueChange = { eventname = it },
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            Box(
                Modifier
                    .weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = "Location",
                        modifier = Modifier.size(30.dp)
                    )
                    TextField(value = location, onValueChange = { location = it })
                }
            }
            Box(
                Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = "Date",
                        modifier = Modifier.size(30.dp)
                    )
                    TextField(value = date, onValueChange = { date = it })
                }
            }
        }
        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_euro),
                    contentDescription = "Fee",
                    modifier = Modifier.size(30.dp)
                )
                TextField(value = fee, onValueChange = { fee = it })
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE1E7EE))
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    painter = painterResource(R.drawable.ic_users),
                    contentDescription = "user",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = "Choose Participants", modifier = Modifier.padding(horizontal = 10.dp))
                Image(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = "gamepad",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE1E7EE))
        ) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Image(
                    painter = painterResource(R.drawable.ic_gamepad),
                    contentDescription = "user",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = "Choose Games", modifier = Modifier.padding(horizontal = 10.dp))
                Image(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = "gamepad",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        Button(
            onClick = {
                viewModel.onAddEvent(
                    AddEventUI(
                        name = eventname,
                        location = location,
                        date = date,
                        fees = fee.toInt()
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFEC814C),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
        ) {
            Text(text = "Save Event")
        }
    }

}

@Preview
@Composable
fun AddEventScreen_Preview() {
    AddEventScreen()
}
