package com.folklore.justincase.domain

import com.folklore.justincase.data.NotesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomNoteRepository(
    private val dao: NotesDao
) : NoteRepository {

    override fun getAll(): Flow<List<Note>> = dao.getAll().map { NoteMapper.fromEntityList(it) }

    override suspend fun getById(id: String): Note? = dao.getById(id)?.let {
        NoteMapper.fromEntity(it)
    }

    override suspend fun insert(note: Note) = dao.insert(NoteMapper.toEntity(note))

    override suspend fun update(note: Note) = dao.update(NoteMapper.toEntity(note))

    override suspend fun delete(note: Note) = dao.delete(NoteMapper.toEntity(note))
}
