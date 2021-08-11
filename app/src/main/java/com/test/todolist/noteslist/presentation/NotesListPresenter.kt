package com.test.todolist.noteslist.presentation

import com.test.todolist.base.BasePresenter
import com.test.todolist.noteslist.data.Note
import com.test.todolist.noteslist.data.NotesRepository
import com.test.todolist.noteslist.data.NotesRepository.Companion.PAGE_LIMIT
import com.test.todolist.noteslist.view.NotesListTarget
import javax.inject.Inject

class NotesListPresenter @Inject constructor(val notesRepository: NotesRepository) :
    BasePresenter<NotesListTarget>() {

    /*
    * This variable might be used fo pagination load
    * */
    private var page = 0

    /*
    * Calls after activity create
    * */
    override fun onCreate() {
        loadNotes()
    }

    fun onListEndReached() {
        // It is a convenient way to load more data
        // however current API doesn't support pagination.
        // Otherwise it would be enough just call
        // method 'loadNotes()' to load next page.
    }

    /*
    * Add subscription to load notes and catch error state.
    *
    * */
    private fun loadNotes() {
        addSubscription(
            notesRepository.getNotes(page * PAGE_LIMIT)
                .subscribe(
                    { notes ->
                        if (notes.isNotEmpty()) {
                            page++
                            target?.addNotes(notes)
                        }
                    }, { target?.showInternetErrorPopUp() })
        )
    }

    /*
    * Called when user clicks on list item
    * */
    fun onItemClicked(note: Note) {
        target?.startOpenedNoteActivity(note.id)
    }

    /*
    * When user clicks on 'Retry' btn in error snackbar
    * */
    fun onRetryRequestClicked() {
        loadNotes()
    }
}