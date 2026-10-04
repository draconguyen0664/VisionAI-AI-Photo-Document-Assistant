package com.visionai

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.visionai.design.VisionTheme
import com.visionai.presentation.home.*

@Composable fun App() = VisionTheme {
    var destination by remember { mutableStateOf("home") }
    if (destination == "home") {
        HomeScreen(
            state = HomeState.Content("Good morning", DemoContent.documents, DemoContent.processing),
            onScan = { destination = "scanner" },
            onTool = { destination = it.name },
            onDocument = { destination = "document/$it" },
        )
    } else {
        Text("VisionAI · ${destination.replace('_', ' ')}")
    }
}
