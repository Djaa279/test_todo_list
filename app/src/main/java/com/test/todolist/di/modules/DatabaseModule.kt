package com.test.todolist.di.modules

import android.app.Application
import androidx.room.Room
import com.test.todolist.noteslist.data.NotesDAO
import com.test.todolist.noteslist.data.NotesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application): NotesDatabase = Room.databaseBuilder(
        app,
        NotesDatabase::class.java, "notes_db"
    ).build()

    @Provides
    @Singleton
    fun provideNotesDao(
        database: NotesDatabase
    ): NotesDAO = database.notesDAO()
}