package com.folklore.justincase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity ORDER BY timestamp DESC")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id = :id LIMIT 1")
    fun getById(id: String): NoteEntity?
}

