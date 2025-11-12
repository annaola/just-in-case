package com.folklore.justincase.domain

import java.time.LocalDateTime

data class Note(
    val id: String,
    val dateTime: LocalDateTime,
    val title: String?,
    val content: String?,
)
