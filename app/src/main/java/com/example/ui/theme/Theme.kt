package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MejunjeLightColorScheme = lightColorScheme(
    primary = MejunjeDeepGreen,
    onPrimary = MejunjeWarmWhite,
    primaryContainer = MejunjeLightGreen,
    onPrimaryContainer = MejunjeDeepGreen,
    secondary = MejunjeAmber,
    onSecondary = MejunjeCharcoal,
    secondaryContainer = MejunjeLightAmber,
    onSecondaryContainer = MejunjeCharcoal,
    tertiary = MejunjeTerracotta,
    onTertiary = MejunjeWarmWhite,
    tertiaryContainer = MejunjeIvory,
    onTertiaryContainer = MejunjeCharcoal,
    background = MejunjeWarmWhite,
    onBackground = MejunjeCharcoal,
    surface = MejunjeWhite,
    onSurface = MejunjeCharcoal,
    surfaceVariant = MejunjeIvory,
    onSurfaceVariant = MejunjeTextSecondary,
    outline = MejunjeBorder,
    outlineVariant = MejunjeBeige
)

private val MejunjeDarkColorScheme = darkColorScheme(
    primary = MejunjeSage,
    onPrimary = MejunjeCharcoal,
    primaryContainer = MejunjeDeepGreen,
    onPrimaryContainer = MejunjeWarmWhite,
    secondary = MejunjeAmber,
    onSecondary = MejunjeCharcoal,
    secondaryContainer = MejunjeSoftMustard,
    onSecondaryContainer = MejunjeCharcoal,
    tertiary = MejunjeSalmon,
    onTertiary = MejunjeCharcoal,
    background = Color(0xFF191816),
    onBackground = MejunjeWarmWhite,
    surface = Color(0xFF22201D),
    onSurface = MejunjeWarmWhite,
    surfaceVariant = Color(0xFF2E2C28),
    onSurfaceVariant = MejunjeBeige,
    outline = Color(0xFF45423C)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep intentional brand colors
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) MejunjeDarkColorScheme else MejunjeLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
