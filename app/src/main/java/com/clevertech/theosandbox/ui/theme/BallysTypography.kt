package com.ballyscorp.ballylive.ui.common.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.clevertech.theosandbox.R

@Immutable
data class BallysTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val featureTitle: TextStyle,
    val subtitle: TextStyle,
    val body: TextStyle,
    val bodyBold: TextStyle,
    val small: TextStyle,
    val smallBold: TextStyle,
    val smallSemiBold: TextStyle,
    val buttonLarge: TextStyle,
    val buttonSmall: TextStyle,
    val smallBody: TextStyle
)

private val roboto = FontFamily(
    Font(resId = R.font.roboto_bold, weight = FontWeight.W700),
    Font(resId = R.font.roboto_regular, weight = FontWeight.W400),
    Font(resId = R.font.roboto_medium, weight = FontWeight.W600),
)

private val ballyThrill = FontFamily(
    Font(resId = R.font.bally_thrill_bold, weight = FontWeight.W700),
    Font(resId = R.font.bally_thrill_cd_xbold, weight = FontWeight.W800)
)

val BallysAppTypography = BallysTypography(
    h1 = TextStyle(
        fontFamily = ballyThrill,
        fontWeight = FontWeight.W800,
        fontSize = 36.sp,
        lineHeight = 40.sp,
    ),
    h2 = TextStyle(
        fontFamily = ballyThrill,
        fontWeight = FontWeight.W800,
        fontSize = 22.sp,
        lineHeight = 32.sp,
    ),
    h3 = TextStyle(
        fontFamily = ballyThrill,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    ),
    featureTitle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    subtitle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    body = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodyBold = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    small = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W400,
        fontSize = 11.sp,
        lineHeight = 16.sp,
    ),
    smallBold = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W600,
        fontSize = 11.sp,
        lineHeight = 16.sp,
    ),
    buttonLarge = TextStyle(
        fontFamily = ballyThrill,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 18.sp,
    ),
    buttonSmall = TextStyle(
        fontFamily = ballyThrill,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),

    // TODO not in the typography
    smallBody = TextStyle(
        fontFamily = ballyThrill,
        fontWeight = FontWeight.W400,
        fontSize = 11.sp,
        lineHeight = 20.sp,
    ),
    smallSemiBold = TextStyle(
        fontFamily = ballyThrill,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
)
