package com.hfu.bierolympiade.feature.leaderboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfu.bierolympiade.R
import com.hfu.bierolympiade.ui.theme.RsLightOrange


@Composable
fun LeaderboardItem(leaderboardItem: LeaderboardUI, index: Int){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp,  vertical = 10.dp)
    ){
        Column() {
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = index.toString(),
                    modifier = Modifier.padding(end = 10.dp)
                )
                Surface(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(12.dp),
                    shape = CircleShape,
                    color = RsLightOrange
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_play_generic),
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Text(
                    text = leaderboardItem.playerName
                )
            }
        }
        Column() {
            Text(
                text = leaderboardItem.points.toString()
            )
        }
    }
}

@Preview
@Composable
fun LeaderboardItemPreview() {
    LeaderboardItem(
        LeaderboardUI(
            playerName = "Felix",
            points = 5,
        ), 5
    )
}
