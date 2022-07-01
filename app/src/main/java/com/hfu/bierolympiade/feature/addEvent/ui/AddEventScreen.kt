package com.hfu.bierolympiade.feature.addEvent.ui

import android.widget.CalendarView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun AddEventScreen(viewModel: AddEventViewModel = viewModel()) {
    val event by viewModel.bindUi(LocalContext.current).observeAsState()
    val games by viewModel.bindUiGames(LocalContext.current).observeAsState()
    AddEventScreenUi(
        viewModel.eventId,
        event,
        games,
        viewModel::saveEvent,
        viewModel::onSaveEvent,
        viewModel::deleteGame,
        viewModel::onDiscard
    )
}

@Composable
fun AddEventScreenUi(
    eventId: String?,
    event: Event?,
    games: List<Game>?,
    saveEvent: (eventId: EventId, name: String, location: String, date: String, fees: Int, isTemporary: Boolean) -> Unit,
    onSaveEvent: (eventId: EventId, name: String, location: String, date: String, fees: Int) -> Unit,
    deleteGameById: (gameId: GameId) -> Unit,
    onDiscard: () -> Unit
) {
    var name: String by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var fees by remember { mutableStateOf("") }

    val gamesList: MutableState<List<Game>> = remember { mutableStateOf(emptyList()) }
    if(games != null){
        gamesList.value = games
    }

    val scrollState = rememberLazyListState()

    if (event != null && name == "") {
        name = event.name
        location = event.location
        date = event.date
        fees = event.fees.toString()
    }

    var showDatePicker by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.background(RsBackground)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .background(RsWhite)
        ) {
            Text(
                text = "Add new Event",
                style = MaterialTheme.typography.h1
            )
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
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 0.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            if (showDatePicker) {
                DatePicker(
                    onDateSelected = { date = it.toString() },
                    onDismissRequest = { showDatePicker = false })
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
                    label = { Text(text = "Event Name") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = RsDarkBlue
                    ),
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
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = RsDarkBlue
                            ),
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
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            showDatePicker = true
                                        }
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = RsDarkBlue
                            ),
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
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = RsDarkBlue
                        ),
                        value = fees,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { fees = it },
                        label = { Text("Fees (optional)") })
                }
            }
            Box {
                /*if (addedPlayerArray != null) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "Participants " + (addedPlayerArray.size - 1),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }*/
                Button(
                    onClick = {
                        eventId?.let { EventId(it) }
                            ?.let {
                                try {
                                    saveEvent(it, name, location, date, fees.toInt(), true)
                                } catch (e: NumberFormatException) {
                                    saveEvent(it, name, location, date, 0, true)
                                }
                            }
                        navControllerGlobal?.popBackStack()
                        navControllerGlobal?.navigate("addPlayerToEvent/${eventId}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                        .height(75.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackground),
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(
                            painter = painterResource(R.drawable.ic_users),
                            contentDescription = "user",
                            tint = RsDarkBlue,
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = "Choose Participants",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)

                        )
                        Image(
                            painter = painterResource(R.drawable.ic_chevron_right),
                            contentDescription = "arrow",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
            Button(
                onClick = {
                    eventId?.let { EventId(it) }
                        ?.let {
                            try {
                                saveEvent(it, name, location, date, fees.toInt(), true)
                            } catch (e: NumberFormatException) {
                                saveEvent(it, name, location, date, 0, true)
                            }
                        }
                    navControllerGlobal?.navigate("pickGameType?eventId=${eventId}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 5.dp)
                    .height(75.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = RsButtonBackground),
                shape = MaterialTheme.shapes.small
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Icon(
                        painter = painterResource(R.drawable.ic_gamepad),
                        contentDescription = "gamepad",
                        tint = RsDarkBlue,
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "Choose Games",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_chevron_right),
                        contentDescription = "arrow",
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            if (!games.isNullOrEmpty()) {
                LazyColumn(state = scrollState, modifier = Modifier.height(200.dp)) {
                    items(gamesList.value) { game ->
                        GameItem(
                            game.name,
                            game.id,
                            deleteGameById,
                            {},
                            gamesList
                        )
                    }
                }

            }
            Button(
                onClick = {
                    eventId?.let { EventId(it) }
                        ?.let {
                            try {
                                onSaveEvent(it, name, location, date, fees.toInt())
                            } catch (e: NumberFormatException) {
                                onSaveEvent(it, name, location, date, 0)
                            }
                        }
                    navControllerGlobal?.popBackStack()
                    navControllerGlobal?.navigate("events")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = RsDarkOrange,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Save Event",
                    style = MaterialTheme.typography.h3,
                    color = RsWhite
                )
            }
        }
    }
}


@Preview
@Composable
fun AddEventScreen_Preview() {
    BierolympiadeTheme() {
        AddEventScreenUi("a59c0e7b-3a58-4859-934d-1a0393835637",
            Event.create(
                id = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
                name = "Bierolympiade",
                location = "Furtwangen",
                date = "06.05.22",
                fees = 10,
                isTemporary = false,
                matches = emptyList(),
                games = emptyList(),
                players = emptyList(),
            ),
            emptyList<Game>(

            ),
            { _, _, _, _, _, _ -> },
            { _, _, _, _, _ -> },
            {_ ->},
            {})
    }
}

@Composable
fun DatePicker(onDateSelected: (LocalDate) -> Unit, onDismissRequest: () -> Unit) {
    val selDate = remember { mutableStateOf(LocalDate.now()) }

    Dialog(onDismissRequest = { onDismissRequest() }, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            Column(
                Modifier
                    .defaultMinSize(minHeight = 72.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Select date".uppercase(Locale.ENGLISH),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = selDate.value.format(DateTimeFormatter.ofPattern("MMM d, YYYY")),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onPrimary
                )

                Spacer(modifier = Modifier.size(16.dp))
            }

            CustomCalendarView(onDateSelected = {
                selDate.value = it
            })

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = "Cancel",
                    )
                }

                TextButton(
                    onClick = {
                        onDateSelected(selDate.value)
                        onDismissRequest()
                    }
                ) {
                    Text(
                        text = "OK",
                    )
                }

            }
        }
    }
}

@Composable
fun CustomCalendarView(onDateSelected: (LocalDate) -> Unit) {
    // Adds view to Compose

    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.wrapContentSize(),
        update = { views ->
            views.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(
                    LocalDate
                        .now()
                        .withMonth(month + 1)
                        .withYear(year)
                        .withDayOfMonth(dayOfMonth)
                )
            }
        }
    )
}