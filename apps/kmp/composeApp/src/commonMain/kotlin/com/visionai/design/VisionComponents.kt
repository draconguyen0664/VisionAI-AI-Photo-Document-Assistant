package com.visionai.design

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

val AiGradient = Brush.linearGradient(listOf(VisionColors.AiBlue, VisionColors.AiViolet, VisionColors.AiPink))

@Composable fun VisionGradientButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier.clip(RoundedCornerShape(18.dp)).background(AiGradient).clickable(onClick = onClick).padding(horizontal = 22.dp, vertical = 15.dp), contentAlignment = Alignment.Center) {
        Text(text, color = VisionColors.TextPrimary, fontWeight = FontWeight.Bold)
    }
}

@Composable fun SectionHeader(title: String, action: String? = null, onAction: () -> Unit = {}) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
        if (action != null) Text(action, color = VisionColors.AiBlue, modifier = Modifier.clickable(onClick = onAction).padding(8.dp))
    }
}
