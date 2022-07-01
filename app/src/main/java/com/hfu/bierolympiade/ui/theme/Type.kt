package com.hfu.bierolympiade.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hfu.bierolympiade.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

private val PlusJarkataSans = FontFamily(
    Font(R.font.plusjakartasans_regular, FontWeight.Normal),
    Font(R.font.plusjakartasans_medium, FontWeight.Medium),
    Font(R.font.plusjakartasans_bold, FontWeight.Bold)
)

val PlusJarkataSansTypography = Typography(

    defaultFontFamily = PlusJarkataSans,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        color = RsDarkBlue
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = RsDarkBlue
    )
)
