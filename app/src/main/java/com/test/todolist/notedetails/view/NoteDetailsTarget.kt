package com.test.todolist.notedetails.view

import androidx.lifecycle.LifecycleOwner

interface NoteDetailsTarget : LifecycleOwner{

    fun setTitle(title: String)
    fun setDueOn(dueOn: String)
    fun setId(id: String)
    fun setUserId(userId: String)
    fun setStatus(status: String)

}