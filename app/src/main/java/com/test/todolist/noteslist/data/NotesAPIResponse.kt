package com.test.todolist.noteslist.data

data class NotesAPIResponse(
    val code: Int?,
    val data: List<Note>?,
    val meta: Meta?
)