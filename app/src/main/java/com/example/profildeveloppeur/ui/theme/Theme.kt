package com.example.profildeveloppeur.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Le thème clair
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFC2185B),
    onPrimary = Color.White,
    secondary = Color(0xFF8E4A5E),
    background = Color(0xFFFFF8F9),
    onBackground = Color(0xFF201A1B),
    surface = Color(0xFFFFF8F9),
    onSurface = Color(0xFF201A1B)
)

// Le thème sombre
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB1C8),
    onPrimary = Color(0xFF5E1133),
    secondary = Color(0xFFE3BDC6),
    background = Color(0xFF191113),
    onBackground = Color(0xFFEFDFE1),
    surface = Color(0xFF191113),
    onSurface = Color(0xFFEFDFE1)
)

// Les formes arrondies pour les cartes et autres composants
private val ProfilShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)

// Le setup du thème en fonction des options du téléphone
@Composable
fun ProfilDeveloppeurTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = ProfilShapes,
        content = content
    )
}