package com.hfu.bierolympiade.feature.addEvent.ui

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
import com.hfu.bierolympiade.ui.theme.*

@Composable
fun AddEventScreen(viewModel: AddEventViewModel = viewModel()) {
    AddEventScreenUi(viewModel::onAddEvent)
}

@Composable
fun AddEventScreenUi(onAddEvent: (name: String, location: String, date: String, fees: Int) -> Unit) {
    var eventname by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var fees by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(25.dp)
            .background(RsBackground),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .background(RsWhite)
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
                            color =
                            RsLightOrange
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

            OutlinedTextField(
                value = eventname,
                onValueChange = { eventname = it },
                modifier = Modifier.padding(horizontal = 10.dp),
                label = { Text(text = "Event Name") }
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
                    OutlinedTextField(
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.ic_location),
                                contentDescription = "Location",
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        value = location,
                        onValueChange = { location = it },
                        label = { Text("Location") })
                }
            }
            Box(
                Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    OutlinedTextField(
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.ic_calendar),
                                contentDescription = "Date",
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        value = date,
                        onValueChange = { date = it },
                        label = {
                            Text("Date")
                        })
                }
            }
        }
        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {

                OutlinedTextField(
                    leadingIcon = {
                        Image(
                            painter = painterResource(R.drawable.ic_euro),
                            contentDescription = "Fee"
                        )
                    },
                    value = fees,
                    onValueChange = { fees = it },
                    label = { Text("Fees (optional)") })
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackround)
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
            colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackround)
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
                onAddEvent(
                    eventname, location, date, fees.toInt()
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RsDarkOrange,
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
    AddEventScreenUi({ _, _, _, _ -> })
}
