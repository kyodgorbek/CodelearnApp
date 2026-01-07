package com.example.codelearnapp.domain.preview.parser

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codelearnapp.domain.preview.model.*

object ComposeCodeParser {

    fun parse(code: String): QirNode {
        // Wrap everything in a default root Column to ensure we return a single node
        val rootChildren = parseBlock(code)
        if (rootChildren.size == 1) return rootChildren.first()
        return QirColumn(QirModifier(padding = 16.dp, width = Dimension.Fill, height = Dimension.Fill), rootChildren)
    }

    private fun parseBlock(code: String): List<QirNode> {
        val nodes = mutableListOf<QirNode>()
        var remaining = code.trim()

        while (remaining.isNotEmpty()) {
            val nextComponent = findNextComponent(remaining)
            if (nextComponent == null) {
                // No more components found
                break
            }

            val (type, args, body, rest) = nextComponent
            val modifier = parseModifiers(args)
            
            val node: QirNode? = when (type) {
                "Column" -> {
                    val vertArrangement = parseArrangement(args, "verticalArrangement") ?: QirArrangement.Top
                    val horizAlign = parseHorizontalAlignment(args) ?: QirAlignment.Horizontal.Start
                    QirColumn(modifier, parseBlock(body), vertArrangement, horizAlign)
                }
                "Row" -> {
                    val horizArrangement = parseArrangement(args, "horizontalArrangement") ?: QirArrangement.Start
                    val vertAlign = parseVerticalAlignment(args) ?: QirAlignment.Vertical.Top
                    QirRow(modifier, parseBlock(body), horizArrangement, vertAlign)
                }
                "Box" -> {
                    val contentAlign = parseBoxAlignment(args, "contentAlignment") ?: QirAlignment.TopStart
                    QirBox(modifier, parseBlock(body), contentAlign)
                }
                "Button" -> {
                    val contentNodes = parseBlock(body)
                    // Take first child or wrap
                    val content = if (contentNodes.isEmpty()) null else if (contentNodes.size == 1) contentNodes.first() else QirRow(QirModifier(), contentNodes)
                    QirButton(modifier, content)
                }
                "Card" -> QirCard(modifier, parseBlock(body))
                "Text" -> {
                    val text = extractStringArg(args) ?: "Text"
                    QirText(modifier, text)
                }
                "Spacer" -> QirSpacer(modifier)
                else -> null
            }

            if (node != null) {
                nodes.add(node)
            }
            remaining = rest.trim()
        }
        return nodes
    }

    // ... existing findNextComponent ...

    // ... existing findMatchingBracket ...

    // ... existing extractStringArg ...

    private fun parseModifiers(args: String): QirModifier {
        var padding = 0.dp
        var width: Dimension = Dimension.Wrap
        var height: Dimension = Dimension.Wrap
        var color: Color? = null
        var shape: QirShape = QirShape.Rectangle
        var align: QirAlignment? = null
        
        // Size / Width / Height
        if (args.contains("fillMaxSize")) {
            width = Dimension.Fill
            height = Dimension.Fill
        }
        if (args.contains("fillMaxWidth")) width = Dimension.Fill
        if (args.contains("fillMaxHeight")) height = Dimension.Fill
        
        // .width(50.dp)
        val widthVal = """width\s*\(\s*(\d+)""".toRegex().find(args)?.groupValues?.get(1)?.toIntOrNull()
        if (widthVal != null) width = Dimension.Fixed(widthVal.dp)

        // .height(50.dp)
        val heightVal = """height\s*\(\s*(\d+)""".toRegex().find(args)?.groupValues?.get(1)?.toIntOrNull()
        if (heightVal != null) height = Dimension.Fixed(heightVal.dp)

        // .size(50.dp)
        val sizeVal = """size\s*\(\s*(\d+)""".toRegex().find(args)?.groupValues?.get(1)?.toIntOrNull()
        if (sizeVal != null) {
            width = Dimension.Fixed(sizeVal.dp)
            height = Dimension.Fixed(sizeVal.dp)
        }

        // Padding
        val padVal = """padding\s*\(\s*(\d+)""".toRegex().find(args)?.groupValues?.get(1)?.toIntOrNull()
        if (padVal != null) padding = padVal.dp
        
        // Background
        if (args.contains("background")) {
             if (args.contains("Color.Red")) color = Color.Red
             if (args.contains("Color.Blue")) color = Color.Blue
             if (args.contains("Color.Green")) color = Color.Green
             if (args.contains("Color.Gray")) color = Color.Gray
             if (args.contains("Color.Yellow")) color = Color.Yellow
             if (args.contains("Color.Black")) color = Color.Black
             if (args.contains("Color.White")) color = Color.White
             if (args.contains("Color.Cyan")) color = Color.Cyan
             if (args.contains("Color.Magenta")) color = Color.Magenta
        }
        
        // Align (Box scope) like .align(Alignment.Center)
        if (args.contains(".align(")) {
            val alignArg = args.substringAfter(".align(").substringBefore(")").trim()
            align = parseBoxAlignmentArg(alignArg)
        }

        return QirModifier(padding, width, height, color, shape, align)
    }
    
    // --- Helpers for Alignment & Arrangement ---

    private fun parseArrangement(args: String, key: String): QirArrangement? {
        // key = "verticalArrangement" etc.
        if (!args.contains(key)) return null
        val value = args.substringAfter(key).substringAfter("=").substringBefore(",").substringBefore(")").trim()
        
        return when {
            value.contains("Arrangement.Center") -> QirArrangement.Center
            value.contains("Arrangement.Start") -> QirArrangement.Start
            value.contains("Arrangement.End") -> QirArrangement.End
            value.contains("Arrangement.Top") -> QirArrangement.Top
            value.contains("Arrangement.Bottom") -> QirArrangement.Bottom
            value.contains("Arrangement.SpaceBetween") -> QirArrangement.SpaceBetween
            value.contains("Arrangement.SpaceAround") -> QirArrangement.SpaceAround
            value.contains("Arrangement.SpaceEvenly") -> QirArrangement.SpaceEvenly
            else -> null
        }
    }

    private fun parseHorizontalAlignment(args: String): QirAlignment.Horizontal? {
        if (!args.contains("horizontalAlignment")) return null
        val value = args.substringAfter("horizontalAlignment").substringAfter("=").substringBefore(",").substringBefore(")").trim()
        return when {
            value.contains("Alignment.CenterHorizontally") -> QirAlignment.Horizontal.CenterHorizontally
            value.contains("Alignment.Start") -> QirAlignment.Horizontal.Start
            value.contains("Alignment.End") -> QirAlignment.Horizontal.End
            else -> null
        }
    }

    private fun parseVerticalAlignment(args: String): QirAlignment.Vertical? {
        if (!args.contains("verticalAlignment")) return null
        val value = args.substringAfter("verticalAlignment").substringAfter("=").substringBefore(",").substringBefore(")").trim()
        return when {
            value.contains("Alignment.CenterVertically") -> QirAlignment.Vertical.CenterVertically
            value.contains("Alignment.Top") -> QirAlignment.Vertical.Top
            value.contains("Alignment.Bottom") -> QirAlignment.Vertical.Bottom
            else -> null
        }
    }

    private fun parseBoxAlignment(args: String, key: String): QirAlignment? {
        if (!args.contains(key)) return null
        val value = args.substringAfter(key).substringAfter("=").substringBefore(",").substringBefore(")").trim()
        return parseBoxAlignmentArg(value)
    }

    private fun parseBoxAlignmentArg(value: String): QirAlignment? {
        return when {
            value.contains("Alignment.TopStart") -> QirAlignment.TopStart
            value.contains("Alignment.TopCenter") -> QirAlignment.TopCenter
            value.contains("Alignment.TopEnd") -> QirAlignment.TopEnd
            value.contains("Alignment.CenterStart") -> QirAlignment.CenterStart
            value.contains("Alignment.Center") -> QirAlignment.Center
            value.contains("Alignment.CenterEnd") -> QirAlignment.CenterEnd
            value.contains("Alignment.BottomStart") -> QirAlignment.BottomStart
            value.contains("Alignment.BottomCenter") -> QirAlignment.BottomCenter
            value.contains("Alignment.BottomEnd") -> QirAlignment.BottomEnd
            else -> null
        }
    }

    data class ParseResult(val type: String, val args: String, val body: String, val rest: String)
}
