package com.folklore.justincase.data.di

import android.content.Context
import androidx.room.Room
import com.folklore.justincase.data.JustInCaseDatabase
import com.folklore.justincase.data.NotesDao
import com.folklore.justincase.domain.NoteRepository
import com.folklore.justincase.domain.RoomNoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    internal fun provideDatabase(@ApplicationContext context: Context): JustInCaseDatabase {
        return Room.databaseBuilder(context, JustInCaseDatabase::class.java, "just_in_case.db")
            .build()
    }

    @Provides
    internal fun provideNotesDao(db: JustInCaseDatabase): NotesDao = db.notesDao()

    @Provides
    @Singleton
    internal fun provideNoteRepository(dao: NotesDao): NoteRepository = RoomNoteRepository(dao)
}
