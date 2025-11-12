package com.folklore.justincase.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
internal abstract class JustInCaseDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
