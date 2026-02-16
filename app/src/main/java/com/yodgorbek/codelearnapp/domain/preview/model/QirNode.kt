package com.yodgorbek.codelearnapp.domain.preview.model

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
    val children: List<QirNode>,
    val verticalArrangement: QirArrangement = QirArrangement.Top,
    val horizontalAlignment: QirAlignment.Horizontal = QirAlignment.Horizontal.Start
) : QirNode()

data class QirRow(
    override val modifier: QirModifier,
    val children: List<QirNode>,
    val horizontalArrangement: QirArrangement = QirArrangement.Start,
    val verticalAlignment: QirAlignment.Vertical = QirAlignment.Vertical.Top
) : QirNode()

data class QirBox(
    override val modifier: QirModifier,
    val children: List<QirNode>,
    val contentAlignment: QirAlignment = QirAlignment.TopStart
) : QirNode()

data class QirText(
    override val modifier: QirModifier,
    val text: String,
    val isTitle: Boolean = false
) : QirNode()

data class QirButton(
    override val modifier: QirModifier,
    val content: QirNode?
) : QirNode()

data class QirSpacer(
    override val modifier: QirModifier
) : QirNode()

data class QirCard(
    override val modifier: QirModifier,
    val children: List<QirNode>
) : QirNode()

/**
 * Simplified Modifier representation
 */
data class QirModifier(
    val padding: Dp = 0.dp,
    val width: Dimension = Dimension.Wrap,
    val height: Dimension = Dimension.Wrap,
    val backgroundColor: Color? = null,
    val shape: QirShape = QirShape.Rectangle,
    val align: QirAlignment? = null // For BoxScope alignment
)

sealed class Dimension {
    object Wrap : Dimension()
    object Fill : Dimension()
    data class Fixed(val size: Dp) : Dimension()
}

enum class QirShape {
    Rectangle, Circle, Rounded
}

sealed class QirAlignment {
    object TopStart : QirAlignment()
    object TopCenter : QirAlignment()
    object TopEnd : QirAlignment()
    object CenterStart : QirAlignment()
    object Center : QirAlignment()
    object CenterEnd : QirAlignment()
    object BottomStart : QirAlignment()
    object BottomCenter : QirAlignment()
    object BottomEnd : QirAlignment()

    sealed class Horizontal {
        object Start : Horizontal()
        object CenterHorizontally : Horizontal()
        object End : Horizontal()
    }

    sealed class Vertical {
        object Top : Vertical()
        object CenterVertically : Vertical()
        object Bottom : Vertical()
    }
}

enum class QirArrangement {
    Start, End, Top, Bottom, Center, SpaceBetween, SpaceAround, SpaceEvenly
}


