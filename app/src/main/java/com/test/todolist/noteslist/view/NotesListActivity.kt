package com.test.todolist.noteslist.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.todolist.R
import com.test.todolist.base.BaseActivity
import com.test.todolist.notedetails.view.NoteDetailsActivity
import com.test.todolist.notedetails.view.NoteDetailsActivity.Companion.OPENED_NOTE_ID_KEY
import com.test.todolist.noteslist.data.Note
import com.test.todolist.noteslist.presentation.NotesListPresenter
import com.test.todolist.utils.InfiniteScrollListener
import kotlinx.android.synthetic.main.activity_notes_list.*

class NotesListActivity : BaseActivity<NotesListPresenter, NotesListTarget>(), NotesListTarget {

    override fun getViewResourceId() = R.layout.activity_notes_list

    lateinit var notesAdapter: NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpToolbar()
        setUpRecyclerView()
    }

    /*
    * Setup toolbar: set visible 'back arrow' icon
    * and set listener on this btn to finish activity
    * */
    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setUpRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)

            // Add vertical gray delimiter to RecyclerView
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            // Add listener to load more data when list
            // scrolls to the bottom
            addOnScrollListener(
                InfiniteScrollListener(
                    { presenter.onListEndReached() },
                    layoutManager as LinearLayoutManager
                )
            )

            notesAdapter = NotesListAdapter { presenter.onItemClicked(it) }
                .apply {
                    adapter = this
                }
        }
    }

    /*
    * Display retrieved list of notes
    * */
    override fun addNotes(notesList: List<Note>) {
        notesAdapter.addNotes(notesList)
    }

    /*
    * Open activity with note details
    * */
    override fun startOpenedNoteActivity(id: Int) {
        startActivity(Intent(this, NoteDetailsActivity::class.java).apply {
            putExtra(OPENED_NOTE_ID_KEY, id)
        })
    }

    /*
    * Display error snackbar with 'Retry' btn
    * */
    override fun showInternetErrorPopUp() {
        Snackbar.make(view_parent, R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) { presenter.onRetryRequestClicked() }
            .show()
    }

}