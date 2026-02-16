package com.yodgorbek.codelearnapp.presentation.tutor.analysis

/**
 * MVP Rule-Based Error Detector.
 * Covers basic syntax errors for beginner lessons.
 */
class RuleBasedErrorDetector {

    fun detect(code: String): CodeError? {
        val lines = code.lines()

        detectUnbalancedBraces(code)?.let { return it }
        detectUnbalancedParentheses(code)?.let { return it }
        detectMissingSemicolon(lines)?.let { return it }
        detectIncorrectForLoop(lines)?.let { return it }

        return null
    }

    private fun detectMissingSemicolon(lines: List<String>): CodeError? {
        for ((index, line) in lines.withIndex()) {
            val trimmed = line.trim()

            if (
                trimmed.isNotEmpty() &&
                !trimmed.endsWith(";") &&
                !trimmed.endsWith("{") &&
                !trimmed.endsWith("}") &&
                !trimmed.startsWith("//") && // Ignore comments
                isStatement(trimmed)
            ) {
                return CodeError(
                    errorType = ErrorType.MissingSemicolon,
                    line = index + 1,
                    column = line.length,
                    expectedToken = ";"
                )
            }
        }
        return null
    }

    private fun detectUnbalancedParentheses(code: String): CodeError? {
        val open = code.count { it == '(' }
        val close = code.count { it == ')' }

        return if (open != close) {
            CodeError(
                errorType = ErrorType.UnbalancedParenthesis,
                line = 1, // Simplified for MVP (real version would act on specific line)
                column = 1,
                expectedToken = ")"
            )
        } else null
    }

    private fun detectUnbalancedBraces(code: String): CodeError? {
        val open = code.count { it == '{' }
        val close = code.count { it == '}' }

        return if (open != close) {
            CodeError(
                errorType = ErrorType.UnbalancedBrace,
                line = 1,
                column = 1,
                expectedToken = "}"
            )
        } else null
    }

    private fun detectIncorrectForLoop(lines: List<String>): CodeError? {
        for ((index, line) in lines.withIndex()) {
            val trimmed = line.trim()

            // Heuristic: line starts with "for", has parentheses, but missing semicolons
            if (trimmed.startsWith("for") && trimmed.contains("(") && !trimmed.contains(";")) {
                 // Check if it's a "for each" style loop which uses ':' in Java/Kotlin sometimes or 'in' in Kotlin
                 // Assuming Java-style syntax for this detector as per prompt "int i = 0"
                 // If Kotlin, "for (i in 0..10)" doesn't use semicolons.
                 // The user prompt yodgorbek is "for (int i = 0 i < 10; i++)" which is Java-like syntax.
                 // We will assume this detector targets C-style loops for now.

                 // However, since this is a Kotlin app teaching CS, we should be careful.
                 // If the lesson is Kotlin, this rule might be wrong.
                 // But the prompt specifically asked for this logic: startsWith("for") && !contains(";")
                return CodeError(
                    errorType = ErrorType.IncorrectForLoopStructure,
                    line = index + 1,
                    column = trimmed.indexOf("for") + 1,
                    expectedToken = ";"
                )
            }
        }
        return null
    }

    private fun isStatement(line: String): Boolean {
        // Basic heuristic for C-style/Java statements
        return line.startsWith("int ") ||
               line.startsWith("var ") ||
               line.startsWith("val ") ||
               line.startsWith("String ") ||
               line.contains("=") ||
               line.startsWith("print") ||
               line.startsWith("System.out")
    }
}
