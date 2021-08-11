package com.test.todolist.di.modules

import com.test.todolist.notedetails.view.NoteDetailsActivity
import com.test.todolist.noteslist.view.NotesListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeNotesListActivity(): NotesListActivity

    @ContributesAndroidInjector
    abstract fun contributeNoteDetailsActivity(): NoteDetailsActivity

}