package com.hfu.bierolympiade.feature.event.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsLightOrange


@Composable
fun EventItem(event: EventUI) {
    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navControllerGlobal?.navigate("eventDetail") {
                    navControllerGlobal?.graph?.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
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
                    painter = painterResource(R.drawable.ic_blank_event),
                    contentDescription = "Event",
                    modifier = Modifier.size(80.dp)
                )
            }
            Column {
                Text(
                    text = event.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 5.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(all = 5.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = "location",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 5.dp)
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = event.location,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = "calendar",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 5.dp)
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = event.date,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                }
                Row(modifier = Modifier.padding(all = 5.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_users),
                        contentDescription = "participants",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 5.dp)
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "2 Participiants",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
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
            "22.02.22"
        )
    )
}