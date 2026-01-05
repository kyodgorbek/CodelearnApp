package com.example.codelearnapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CodeExecutionRequest(
    val script: String,
    val language: String,
    val versionIndex: String = "0"
)

@Serializable
data class CodeExecutionResponse(
    val output: String?,
    val statusCode: Int? = null,
    val memory: String? = null,
    val cpuTime: String? = null,
    val error: String? = null
)
