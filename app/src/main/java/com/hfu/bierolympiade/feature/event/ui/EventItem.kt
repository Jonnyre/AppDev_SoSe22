package com.hfu.bierolympiade.feature.event.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.EventId


@Composable
fun EventItem(event: EventUI) {
    Card(
        elevation = 3.dp,
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
                Image(
                    painter = painterResource(R.drawable.ic_blank_event),
                    contentDescription = "blank_event",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFFF2C299))
                        .clip(RoundedCornerShape(20.dp))

            )
            
            Column {
                Text(
                    text = event.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 5.dp))
                Row(modifier = Modifier.padding(all = 5.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = "location",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 5.dp))
                    Text(
                        text = event.location,
                    modifier = Modifier.padding(end = 5.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = "calendar",
                        modifier = Modifier.size(20.dp)
                            .padding(end = 5.dp))
                    Text(text = event.date)
                }
                Row (modifier = Modifier.padding(all = 5.dp)){
                    Image(
                        painter = painterResource(R.drawable.ic_users),
                        contentDescription = "participants",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 5.dp))
                    Text(text = event.participants.toString() + " Participiants")
                }


            }
        }
    }
}

@Preview
@Composable
fun EventItem_Preview() {
    EventItem(
        EventUI(
        EventId("foo"),
        "test",
        "eine test beschreibung",
            "22.02.22",
            14
    )
    )
}