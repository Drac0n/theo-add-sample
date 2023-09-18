package com.ballyscorp.ballylive.ui.common.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
data class BallysColorScheme(
    private val isDarkTheme: Boolean,
    private val colorScheme: ColorScheme,

    val buttonContainerColor: Color = colorScheme.primary,
    val buttonContentColor: Color = colorScheme.onPrimary,
    val buttonDisabledContainerColor: Color = colorScheme.onSurface.copy(alpha = 0.12f),
    val buttonDisabledContentColor: Color = colorScheme.onSurface.copy(alpha = 0.38f),

    // Bottom Navigation Bar
    val bottomBarContainerColor: Color = Color(color = 0xFF191919),
    val bottomBarIconColorSelected: Color = Color(color = 0xFF0290B6),
    val bottomBarIconColorUnselected: Color = Color(color = 0xFF646B6C),

    // Live Top Bar
    val liveIconTint: Color = Color(color = 0xFFE5002B),
    val regularIconTint: Color = Color(color = 0xFFFFFFFF),

    // Login
    val grey2Color: Color = Color(color = 0xFF121212),
    val grey3Color: Color = Color(color = 0xFFBDC0C2),
    val grey4Color: Color = Color(color = 0xFF1D1D1D),
    val grey6Color: Color = Color(color = 0xFF454545),
    val blue3Color: Color = Color(color = 0xFF105F75),
    val blue4Color: Color = Color(color = 0xFF0290B6),
    val closeButtonColor: Color = Color(color = 0x80505050),
    val digitErrorColor: Color = Color(color = 0x33F1493F),

    // Discussion Board
    val divisionLineColor: Color = Color(color = 0xFF646B6C),
    val containerTextFieldColor: Color = Color(color = 0xFF161616),
    val commentLabelTextFieldColor: Color = Color(color = 0xFF454545),
    val followContentColor: Color = Color(color = 0xFFFFFFFF),

    // Live Chat
    val containerBackGround: Color = Color.White,
    val messageBackgroundColor: Color = Color.White,
    val privateOwnMessageBackgroundColor: Color = Color.LightGray,
    val privateMessageBackgroundColor: Color = Color(color = 0xFF200833),
    val userNameColor: Color = Color(color = 0XFF736A90),
    val messageColorOwn: Color = Color.Black,
    val messageColor: Color = Color.White,
    val textFieldTopDividerLinerColor: Color = Color.Transparent,
    val textFieldTextContainerColor: Color = Color.White,
    val textFieldDisabledContainerColor: Color = Color(0xFFF2F0F3),
    val textFieldFocusedContainerColor: Color = Color(0xFFF2F0F3),
    val textFieldUnfocusedContainerColor: Color = Color(0xFFF2F0F3),
    val textFieldContainerBorderColor: Color = Color.Transparent,
    val textFieldFocusedTextColor: Color = Color(0XFF200833),
    val textFieldUnfocusedTextColor: Color = Color(0XFF200833),
    val textFieldFocusedPlaceholderColor: Color = Color(0XFF736A90),
    val textFieldUnfocusedPlaceholderColor: Color = Color(0XFF736A90),
    val textFieldFocusedIndicatorColor: Color = Color.Transparent,
    val textFieldUnfocusedIndicatorColor: Color = Color.Transparent,
    val textFieldDisabledIndicatorColor: Color = Color.Transparent,
    val textFieldButtonContainerColor: Color = Color(0xFF0290B6),
    val textFieldButtonDisabledContainerColor: Color = Color.Transparent,
    val textFieldButtonContentColor: Color = Color.White,
    val textFieldButtonDisabledContentColor: Color = Color.Transparent,
    val textFieldCursorColor: Color = Color(0XFF3E285F),

    // Live Chat Landscape
    val landscapeContainerBackGround: Color = Color.Transparent,
    val landscapeMessageBackgroundColor: Color = Color.Transparent,
    val landscapeUserNameColor: Color = Color(color = 0XAAFFFFFF),
    val landscapeMessageColorOwn: Color = Color.White,
    val landscapeTextFieldTextContainerColor: Color = Color.Transparent,
    val landscapeTextFieldDisabledContainerColor: Color = Color.Transparent,
    val landscapeTextFieldFocusedContainerColor: Color = Color.Transparent,
    val landscapeTextFieldUnfocusedContainerColor: Color = Color.Transparent,
    val landscapeTextFieldContainerBorderColor: Color = Color(color = 0X55FFFFFF),
    val landscapeTextFieldFocusedTextColor: Color = Color.White,
    val landscapeTextFieldUnfocusedTextColor: Color = Color.White,
    val landscapeTextFieldFocusedPlaceholderColor: Color = Color(color = 0XAAFFFFFF),
    val landscapeTextFieldUnfocusedPlaceholderColor: Color = Color(color = 0XAAFFFFFF),
    val landscapeTextFieldCursorColor: Color = Color.White
)
