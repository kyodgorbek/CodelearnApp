package com.example.codelearnapp.presentation.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.codelearnapp.domain.preview.model.*

@Composable
fun MobilePreview(
    rootNode: QirNode,
    modifier: Modifier = Modifier
) {
    // Phone Frame
    Box(
        modifier = modifier
            .width(320.dp) // Phone width
            .height(640.dp) // Phone height
            .clip(RoundedCornerShape(32.dp))
            .background(Color.Black)
            .padding(12.dp) // Bezel
    ) {
        // Screen Content
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            color = Color.White
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                QirRenderer(rootNode)
            }
        }
        
        // Notch / Camera (Visual decoration)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(100.dp)
                .height(24.dp)
                .background(Color.Black, RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
        )
    }
}

@Composable
fun QirRenderer(node: QirNode) {
    // Convert QIR modifier to Compose Modifier
    val composeModifier = node.modifier.toComposeModifier()

    when (node) {
        is QirColumn -> {
            Column(modifier = composeModifier) {
                node.children.forEach { child ->
                    QirRenderer(child)
                }
            }
        }
        is QirRow -> {
            Row(modifier = composeModifier) {
                node.children.forEach { child ->
                    QirRenderer(child)
                }
            }
        }
        is QirBox -> {
            Box(modifier = composeModifier) {
                node.children.forEach { child ->
                    QirRenderer(child)
                }
            }
        }
        is QirText -> {
            Text(
                text = node.text,
                modifier = composeModifier,
                color = Color.Black // Default to black on white screen
            )
        }
        is QirButton -> {
            Button(
                onClick = {}, // Visual only
                modifier = composeModifier
            ) {
                if (node.content != null) {
                    QirRenderer(node.content)
                } else {
                    Text("Button")
                }
            }
        }
        is QirSpacer -> {
            Spacer(modifier = composeModifier)
        }
    }
}

@Composable
fun QirModifier.toComposeModifier(): Modifier {
    var mod = Modifier
        .padding(this.padding)
    
    // Size
    // Width
    mod = when (this.width) {
        is Dimension.Fill -> mod.fillMaxWidth()
        is Dimension.Fixed -> mod.width((this.width as Dimension.Fixed).size)
        is Dimension.Wrap -> mod.wrapContentWidth()
    }
    
    // Height
    mod = when (this.height) {
        is Dimension.Fill -> mod.fillMaxHeight()
        is Dimension.Fixed -> mod.height((this.height as Dimension.Fixed).size)
        is Dimension.Wrap -> mod.wrapContentHeight()
    }

    // Background
    this.backgroundColor?.let { color ->
        mod = mod.background(color)
    }

    return mod
}
