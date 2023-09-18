package com.ballyscorp.ballylive.ui.common.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val primary = Color(color = 0xFFEC0000)

private val secondary = Color(color = 0xFFAF0000)

private val tertiary = Color(color = 0xFF200833)

private val uiPalette = UiPalette(
    success = Shades(
        default = Color(color = 0xFF1DD75C),
        light = Color(color = 0x1A1DD75C)
    ),
    error = Shades(
        default = Color(color = 0xFFFF757A),
        light = Color(color = 0xFFFF5258)
    ),
    info = Shades(
        default = Color(color = 0xFF52ADCC),
        light = Color(color = 0xFF0098CB)
    ),
)

private val terrain = Terrain(
    hue = Hue(
        purple1 = Color(color = 0xFF200833),
        purple2 = Color(color = 0xFF3E285F),
        purple3 = Color(color = 0xFF736A90),
        purple4 = Color(color = 0xFFC1BDD0),
        purple5 = Color(color = 0xFFF2F0F3),
    ),
    white = Color(color = 0xFFFFFFFF),
    yellow = Color(color = 0xFFFFCD39),
    green1 = Color(color = 0xFF1DD75C),
    transparent = Transparent(
        dark = Color(color = 0xFF200833),
        white = Color(color = 0x00FFFFFF),
        divider = Color(color = 0x00FFFFFF)
    )
)

private val gradients = Gradients(
    red = Brush.linearGradient(
        listOf(
            Color(color = 0xFFEC0000),
            Color(color = 0xFFAF0000)
        )
    ),
    dark = Brush.linearGradient(
        listOf(
            Color(color = 0xFF3E285F),
            Color(color = 0xFF200833)
        )
    ),
    black = Brush.linearGradient(
        listOf(
            Color(color = 0xFF434344),
            Color(color = 0xFF000000)
        )
    ),
    light = Brush.linearGradient(
        listOf(
            Color(color = 0xFFFFFFFF),
            Color(color = 0xFFE6E5EC)
        )
    ),
    redDark = Brush.verticalGradient(
        listOf(
            Color(0xCCEC0000),
            Color(0xCC200833)
        )
    )
)

internal val BallysLightColorPalette = BallysColors(
    primary = primary,
    secondary = secondary,
    tertiary = tertiary,
    uiPalette = uiPalette,
    terrain = terrain,
    gradients = gradients,
    isLight = true
)
