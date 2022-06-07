package com.hfu.bierolympiade.feature.pickGameType.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.domain.model.GameTypeId
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import com.hfu.bierolympiade.ui.theme.RsLightOrange


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickGameTypeItem(gameType: pickGameTypeUI) {
    Card(
        modifier = Modifier.padding(4.dp),
        onClick = {
            navControllerGlobal?.navigate("addGame/${gameType.id.value}" )
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(
                    RsLightOrange
                )
                .padding(10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_blank_event),
                    contentDescription = "Event",
                    modifier = Modifier.size(55.dp)
                )
                Text(
                    text = gameType.name,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color(R.color.white),
                )

            }

        }

    }
}

@Preview
@Composable
fun PickGameTypeItem_Preview() {
    PickGameTypeItem(
        pickGameTypeUI(
            GameTypeId("foo"),
            "Flunkyball",
            "eine test beschreibung"
        )
    )
}