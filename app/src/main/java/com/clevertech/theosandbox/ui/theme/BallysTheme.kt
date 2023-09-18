package com.ballyscorp.ballylive.ui.common.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val LocalBallysColors = staticCompositionLocalOf {
    BallysLightColorPalette
}

val LocalBallysTypography = staticCompositionLocalOf {
    BallysAppTypography
}

@Composable
fun BallysTheme(
    content: @Composable () -> Unit
) {
    // Use the material theme to provide ripples, shapes, ...
    MaterialTheme(
        colorScheme = debugMaterialColorScheme(),
        shapes = debugMaterialShapes()
    ) {
        // TODO-design: add dark color palette
        CompositionLocalProvider(
            LocalBallysColors provides BallysLightColorPalette,
            LocalBallysTypography provides BallysAppTypography,
            content = content,
        )
    }
}

object BallysTheme {
    val colors: BallysColors
        @Composable
        get() = LocalBallysColors.current
    val typography: BallysTypography
        @Composable
        get() = LocalBallysTypography.current
}

@Composable
fun BallysRippleTheme(
    color: Color = RippleTheme.defaultRippleColor(
        contentColor = BallysTheme.colors.tertiary,
        lightTheme = BallysTheme.colors.isLight
    ),
    alpha: RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = BallysTheme.colors.tertiary,
        lightTheme = BallysTheme.colors.isLight
    ),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRippleTheme provides BallysRippleThemeImpl(
            color = color,
            alpha = alpha
        )
    ) {
        content()
    }
}

@Immutable
private data class BallysRippleThemeImpl(
    val color: Color,
    val alpha: RippleAlpha
) : RippleTheme {

    @Composable
    override fun defaultColor(): Color = color

    @Composable
    override fun rippleAlpha(): RippleAlpha = alpha
}

/**
 * Material color pallet with every color set to be highlighted, since these colors should not be
 * used.
 */
private fun debugMaterialColorScheme(
    debugColor: Color = Color.Magenta
) = ColorScheme(
    primary = debugColor,
    onPrimary = debugColor,
    primaryContainer = debugColor,
    onPrimaryContainer = debugColor,
    inversePrimary = debugColor,
    secondary = debugColor,
    onSecondary = debugColor,
    secondaryContainer = debugColor,
    onSecondaryContainer = debugColor,
    tertiary = debugColor,
    onTertiary = debugColor,
    tertiaryContainer = debugColor,
    onTertiaryContainer = debugColor,
    background = debugColor,
    onBackground = debugColor,
    surface = debugColor,
    onSurface = debugColor,
    surfaceVariant = debugColor,
    onSurfaceVariant = debugColor,
    surfaceTint = debugColor,
    inverseSurface = debugColor,
    inverseOnSurface = debugColor,
    error = debugColor,
    onError = debugColor,
    errorContainer = debugColor,
    onErrorContainer = debugColor,
    outline = debugColor,
    outlineVariant = debugColor,
    scrim = debugColor,
)

private fun debugMaterialShapes() = Shapes(
    extraSmall = CutCornerShape(24.dp),
    small = CutCornerShape(24.dp),
    medium = CutCornerShape(24.dp),
    large = CutCornerShape(24.dp),
    extraLarge = CutCornerShape(24.dp),
)
