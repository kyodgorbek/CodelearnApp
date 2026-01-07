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
    var composeModifier = node.modifier.toComposeModifier()

    // Handle .align specific to BoxScope manually since we can't apply scoped modifiers easily in generic recursion
    // We rely on the parent container handling alignment, OR we just support basic alignment via Box wrapper if needed.
    // For this version (Preview), we'll prioritize Container alignment properties.
    
    when (node) {
        is QirColumn -> {
            Column(
                modifier = composeModifier,
                verticalArrangement = node.verticalArrangement.toComposeVerticalArrangement(),
                horizontalAlignment = node.horizontalAlignment.toComposeHorizontalAlignment()
            ) {
                node.children.forEach { child ->
                    // Handle Child Alignments specific to Column if we implemented them
                    // For now, renderer is simple recursive
                    QirRenderer(child)
                }
            }
        }
        is QirRow -> {
            Row(
                modifier = composeModifier,
                horizontalArrangement = node.horizontalArrangement.toComposeHorizontalArrangement(),
                verticalAlignment = node.verticalAlignment.toComposeVerticalAlignment()
            ) {
                node.children.forEach { child ->
                    QirRenderer(child)
                }
            }
        }
        is QirBox -> {
            Box(
                modifier = composeModifier,
                contentAlignment = node.contentAlignment.toCompose()
            ) {
                node.children.forEach { child ->
                     if (child.modifier.align != null) {
                         Box(modifier = Modifier.fillMaxSize(), contentAlignment = child.modifier.align!!.toCompose()) {
                             QirRenderer(child)
                         }
                     } else {
                         QirRenderer(child)
                     }
                }
            }
        }
        is QirText -> {
            Text(
                text = node.text,
                modifier = composeModifier,
                color = Color.Black
            )
        }
        is QirButton -> {
            Button(
                onClick = {}, 
                modifier = composeModifier
            ) {
                if (node.content != null) {
                    QirRenderer(node.content)
                } else {
                    Text("Button")
                }
            }
        }
        is QirCard -> {
            Card(modifier = composeModifier) {
                Column(modifier = Modifier.padding(16.dp)) {
                    node.children.forEach { child ->
                        QirRenderer(child)
                    }
                }
            }
        }
        is QirSpacer -> {
            Spacer(modifier = composeModifier)
        }
    }
}

@Composable
fun QirArrangement.toComposeVerticalArrangement(): Arrangement.Vertical {
    return when(this) {
        QirArrangement.Top -> Arrangement.Top
        QirArrangement.Bottom -> Arrangement.Bottom
        QirArrangement.Center -> Arrangement.Center
        QirArrangement.SpaceBetween -> Arrangement.SpaceBetween
        QirArrangement.SpaceAround -> Arrangement.SpaceAround
        QirArrangement.SpaceEvenly -> Arrangement.SpaceEvenly
        else -> Arrangement.Top // Fallback for vertical
    }
}

@Composable
fun QirArrangement.toComposeHorizontalArrangement(): Arrangement.Horizontal {
    return when(this) {
        QirArrangement.Start -> Arrangement.Start
        QirArrangement.End -> Arrangement.End
        QirArrangement.Center -> Arrangement.Center
        QirArrangement.SpaceBetween -> Arrangement.SpaceBetween
        QirArrangement.SpaceAround -> Arrangement.SpaceAround
        QirArrangement.SpaceEvenly -> Arrangement.SpaceEvenly
        else -> Arrangement.Start
    }
}

@Composable
fun QirAlignment.Horizontal.toComposeHorizontalAlignment(): Alignment.Horizontal {
    return when(this) {
        QirAlignment.Horizontal.Start -> Alignment.Start
        QirAlignment.Horizontal.CenterHorizontally -> Alignment.CenterHorizontally
        QirAlignment.Horizontal.End -> Alignment.End
    }
}

@Composable
fun QirAlignment.Vertical.toComposeVerticalAlignment(): Alignment.Vertical {
    return when(this) {
        QirAlignment.Vertical.Top -> Alignment.Top
        QirAlignment.Vertical.CenterVertically -> Alignment.CenterVertically
        QirAlignment.Vertical.Bottom -> Alignment.Bottom
    }
}

@Composable
fun QirAlignment.toCompose(): Alignment {
    return when(this) {
        QirAlignment.TopStart -> Alignment.TopStart
        QirAlignment.TopCenter -> Alignment.TopCenter
        QirAlignment.TopEnd -> Alignment.TopEnd
        QirAlignment.CenterStart -> Alignment.CenterStart
        QirAlignment.Center -> Alignment.Center
        QirAlignment.CenterEnd -> Alignment.CenterEnd
        QirAlignment.BottomStart -> Alignment.BottomStart
        QirAlignment.BottomCenter -> Alignment.BottomCenter
        QirAlignment.BottomEnd -> Alignment.BottomEnd
        // Fallbacks for type safety if needed (though sealed covers all)
        else -> Alignment.TopStart
    }
}

@Composable
fun QirModifier.toComposeModifier(): Modifier {
    var mod = Modifier
        .padding(this.padding)
// ... (rest of modifier)
    
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
