package com.visionai.design

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object VisionColors {
    val Background = Color(0xFF070B14); val Surface = Color(0xFF101729); val Elevated = Color(0xFF151D32)
    val Interactive = Color(0xFF19233B); val Primary = Color(0xFF3658DF); val AiBlue = Color(0xFF58B8FF)
    val AiViolet = Color(0xFF7657FF); val AiPink = Color(0xFFF45AC8); val Success = Color(0xFF38D996)
    val TextPrimary = Color(0xFFF7F9FF); val TextSecondary = Color(0xFFA8B0C5); val TextTertiary = Color(0xFF717A91)
}

@Composable fun VisionTheme(content: @Composable () -> Unit) {
    val scheme = darkColorScheme(primary = VisionColors.Primary, secondary = VisionColors.AiViolet, background = VisionColors.Background, surface = VisionColors.Surface, onBackground = VisionColors.TextPrimary, onSurface = VisionColors.TextPrimary)
    MaterialTheme(colorScheme = scheme, typography = Typography(), content = content)
}
