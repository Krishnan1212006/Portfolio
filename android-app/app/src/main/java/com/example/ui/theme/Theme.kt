package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = CyanPrimary,
    onPrimary = Color.Black,
    primaryContainer = CardNavySurfaceVariant,
    onPrimaryContainer = CyanPrimary,
    secondary = AccentTeal,
    onSecondary = Color.Black,
    tertiary = CyberPurple,
    onTertiary = Color.White,
    background = DeepNavyBg,
    onBackground = TextWhiteHigh,
    surface = CardNavySurface,
    onSurface = TextWhiteHigh,
    surfaceVariant = CardNavySurfaceVariant,
    onSurfaceVariant = TextWhiteMedium
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = Color.White,
    primaryContainer = LightSurfaceVariant,
    onPrimaryContainer = LightPrimary,
    secondary = Color(0xFF00897B),
    onSecondary = Color.White,
    tertiary = Color(0xFF5E35B1),
    onTertiary = Color.White,
    background = LightBg,
    onBackground = TextDarkHigh,
    surface = LightSurface,
    onSurface = TextDarkHigh,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = TextDarkMedium
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to sleek dark tech theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
