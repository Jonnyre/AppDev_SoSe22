package com.hfu.bierolympiade.feature.addEvent.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.*

@Composable
fun AddEventScreen(viewModel: AddEventViewModel = viewModel()) {
    AddEventScreenUi(viewModel.eventId, viewModel::onSaveEvent, viewModel::onDiscard)
}

@Composable
fun AddEventScreenUi(
    eventId: String?,
    onSaveEvent: (eventId: EventId, name: String, location: String, date: String, fees: Int) -> Unit,
    onDiscard: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var fees by remember { mutableStateOf("") }

    val addedPlayerString = navControllerGlobal?.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("players")?.observeAsState()
    val addedPlayerArray = addedPlayerString?.value?.split(";")

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
                onClick = {
                    onDiscard()
                    navControllerGlobal?.popBackStack()
                },
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
                value = name,
                onValueChange = { name = it },
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { fees = it },
                    label = { Text("Fees (optional)") })
            }
        }
        Box {
            if (addedPlayerArray != null) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "Participants " + (addedPlayerArray.size - 1),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Button(
                onClick = {
                    navControllerGlobal?.popBackStack()
                    navControllerGlobal?.navigate("addPlayerToEvent/${eventId}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
                    .height(75.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackground)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(
                        painter = painterResource(R.drawable.ic_users),
                        contentDescription = "user",
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "Choose Participants", modifier = Modifier
                            .padding(horizontal = 10.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_chevron_right),
                        contentDescription = "gamepad",
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
        Button(
            onClick = { navControllerGlobal?.navigate("pickGameType?eventId=${eventId}") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackground)
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
                eventId?.let { EventId(it) }
                    ?.let {
                        try {
                            onSaveEvent(it, name, location, date, fees.toInt())
                        } catch (e: NumberFormatException) {
                            onSaveEvent(it, name, location, date, 0);
                        }
                    }
                navControllerGlobal?.navigate("events")
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
    AddEventScreenUi("f16cdf15-6528-4a0b-993c-24d5bf8045a7", { _, _, _, _, _ -> }, {})
}