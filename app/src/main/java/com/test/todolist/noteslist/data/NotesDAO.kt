package com.test.todolist.noteslist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface NotesDAO {

    @Query("SELECT * FROM notes")
    fun getNotes(): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id ")
    fun getNote(id: Int): Single<Note?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNotes(notes: List<Note>)

}