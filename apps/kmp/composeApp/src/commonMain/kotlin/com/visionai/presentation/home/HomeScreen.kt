package com.visionai.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.visionai.design.*
import com.visionai.domain.Document

@Composable fun HomeScreen(state: HomeState, onScan: () -> Unit, onTool: (QuickTool) -> Unit, onDocument: (String) -> Unit) {
    LazyColumn(Modifier.fillMaxSize().background(VisionColors.Background), contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 112.dp), verticalArrangement = Arrangement.spacedBy(28.dp)) {
        item { Header() }
        item { Hero(onScan) }
        item { SectionHeader("Quick tools", "See all") }
        item { QuickTools(onTool) }
        when (state) {
            HomeState.Loading -> item { Text("Preparing your workspace…", color = VisionColors.TextSecondary) }
            is HomeState.Error -> item { Text(state.message, color = MaterialTheme.colorScheme.error) }
            is HomeState.Content -> {
                item { SectionHeader("Recent documents", "See all") }
                item { RecentDocuments(state.recent, onDocument) }
                if (state.processing.isNotEmpty()) { item { SectionHeader("Processing") }; items(state.processing, key = { it.id }) { ProcessingCard(it) } }
            }
        }
    }
}

@Composable private fun Header() { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) { Column { Text("VISIONAI", color = VisionColors.AiBlue, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold); Text("Create knowledge", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold) }; Box(Modifier.size(44.dp).clip(RoundedCornerShape(15.dp)).background(AiGradient)) } }

@Composable private fun Hero(onScan: () -> Unit) { Box(Modifier.fillMaxWidth().height(244.dp).clip(RoundedCornerShape(30.dp)).background(Brush.linearGradient(listOf(VisionColors.Elevated, Color(0xFF172A5C), Color(0xFF341B4D)))).padding(24.dp)) { Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) { Column { Text("Turn documents into", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold); Text("AI-powered knowledge", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = VisionColors.AiBlue); Spacer(Modifier.height(12.dp)); Text("Scan, understand and ask anything about your documents.", color = VisionColors.TextSecondary, modifier = Modifier.fillMaxWidth(.78f)) }; VisionGradientButton("Scan with VisionAI  →", onScan) } } }

@Composable private fun QuickTools(onTool: (QuickTool) -> Unit) { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) { QuickTool.entries.forEach { tool -> Column(Modifier.weight(1f).clip(RoundedCornerShape(18.dp)).background(VisionColors.Surface).clickable { onTool(tool) }.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) { Box(Modifier.size(38.dp).clip(RoundedCornerShape(12.dp)).background(VisionColors.Interactive), contentAlignment = Alignment.Center) { Text("✦", color = VisionColors.AiBlue) }; Spacer(Modifier.height(8.dp)); Text(tool.label, style = MaterialTheme.typography.labelMedium, maxLines = 1) } } } }

@Composable private fun RecentDocuments(documents: List<Document>, onOpen: (String) -> Unit) { if (documents.isEmpty()) { Text("Your recent scans will appear here.", color = VisionColors.TextSecondary) } else LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) { items(documents, key = { it.id }) { doc -> Column(Modifier.width(154.dp).clip(RoundedCornerShape(20.dp)).background(VisionColors.Surface).clickable { onOpen(doc.id) }.padding(12.dp)) { Box(Modifier.fillMaxWidth().height(108.dp).clip(RoundedCornerShape(14.dp)).background(Brush.linearGradient(listOf(VisionColors.Interactive, VisionColors.Primary)))); Spacer(Modifier.height(10.dp)); Text(doc.name, maxLines = 1, fontWeight = FontWeight.SemiBold); Text(doc.type.name.lowercase().replace('_', ' '), color = VisionColors.TextTertiary, style = MaterialTheme.typography.labelSmall) } } } }

@Composable private fun ProcessingCard(document: Document) { Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(VisionColors.Surface).padding(16.dp), verticalAlignment = Alignment.CenterVertically) { CircularProgressIndicator(Modifier.size(38.dp), color = VisionColors.AiViolet, strokeWidth = 3.dp); Spacer(Modifier.width(14.dp)); Column { Text(document.name, fontWeight = FontWeight.SemiBold); Text(document.status.name.lowercase().replace('_', ' '), color = VisionColors.TextSecondary) } } }
