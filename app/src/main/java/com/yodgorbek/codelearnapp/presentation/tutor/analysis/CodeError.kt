package com.yodgorbek.codelearnapp.presentation.tutor.analysis

/**
 * Result model for detected code errors.
 */
data class CodeError(
    val errorType: ErrorType,
    val line: Int,
    val column: Int,
    val expectedToken: String? = null
)
