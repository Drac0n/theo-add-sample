package com.ballyscorp.ballylive.ui.common.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class BallysColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val uiPalette: UiPalette,
    val terrain: Terrain,
    val gradients: Gradients,
    val isLight: Boolean,
)

@Immutable
data class UiPalette(
    val success: Shades,
    val error: Shades,
    val info: Shades,
)

@Immutable
data class Terrain(
    val hue: Hue,
    val white: Color,
    val yellow: Color,
    val green1: Color,
    val transparent: Transparent,
)

@Immutable
data class Shades(
    val default: Color,
    val light: Color,
)

@Immutable
data class Hue(
    val purple1: Color,
    val purple2: Color,
    val purple3: Color,
    val purple4: Color,
    val purple5: Color,
)

@Immutable
data class Transparent(
    val dark: Color,
    val white: Color,
    val divider: Color
)

@Immutable
data class Gradients(
    val red: Brush,
    val dark: Brush,
    val black: Brush,
    val light: Brush,
    val redDark: Brush
)
