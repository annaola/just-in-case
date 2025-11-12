package com.folklore.justincase.domain

import com.folklore.justincase.data.NoteEntity
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

internal object NoteMapper {

    fun toEntity(note: Note): NoteEntity = with(note) {
        NoteEntity(
            id = id,
            timestamp = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            title = title,
            content = content
        )
    }

    fun fromEntity(entity: NoteEntity): Note = with(entity) {
        Note(
            id = id,
            dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()
            ),
            title = title,
            content = content
        )
    }

    fun fromEntityList(entities: List<NoteEntity>): List<Note> = entities.map { fromEntity(it) }
}
