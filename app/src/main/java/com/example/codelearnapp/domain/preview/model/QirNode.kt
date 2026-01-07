package com.example.codelearnapp.domain.preview.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * QIR (UI Intermediate Representation) Nodes
 * Platform-agnostic layout tree description.
 */
sealed class QirNode {
    abstract val modifier: QirModifier
}

data class QirColumn(
    override val modifier: QirModifier,
    val children: List<QirNode>
) : QirNode()

data class QirRow(
    override val modifier: QirModifier,
    val children: List<QirNode>
) : QirNode()

data class QirBox(
    override val modifier: QirModifier,
    val children: List<QirNode>
) : QirNode()

data class QirText(
    override val modifier: QirModifier,
    val text: String,
    val isTitle: Boolean = false // Simple typography heuristic
) : QirNode()

data class QirButton(
    override val modifier: QirModifier,
    val content: QirNode? // Button usually has a Text or Row inside
) : QirNode()

data class QirSpacer(
    override val modifier: QirModifier
) : QirNode()

/**
 * Simplified Modifier representation
 */
data class QirModifier(
    val padding: Dp = 0.dp,
    val width: Dimension = Dimension.Wrap,
    val height: Dimension = Dimension.Wrap,
    val backgroundColor: Color? = null,
    val shape: QirShape = QirShape.Rectangle
)

sealed class Dimension {
    object Wrap : Dimension()
    object Fill : Dimension()
    data class Fixed(val size: Dp) : Dimension()
}

enum class QirShape {
    Rectangle, Circle, Rounded
}
