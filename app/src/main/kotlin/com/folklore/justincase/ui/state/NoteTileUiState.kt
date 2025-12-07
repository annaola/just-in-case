package com.folklore.justincase.ui.state

import java.time.LocalDateTime

data class NoteTileUiState(
    val id: String,
    val title: String?,
    val content: String?,
    val dateTime: LocalDateTime
)
