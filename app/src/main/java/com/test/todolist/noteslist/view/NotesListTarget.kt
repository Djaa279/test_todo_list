package com.test.todolist.noteslist.view

import androidx.lifecycle.LifecycleOwner
import com.test.todolist.noteslist.data.Note

interface NotesListTarget : LifecycleOwner {
    fun addNotes(notesList: List<Note>)
    fun startOpenedNoteActivity(id: Int)
    fun showInternetErrorPopUp()
}