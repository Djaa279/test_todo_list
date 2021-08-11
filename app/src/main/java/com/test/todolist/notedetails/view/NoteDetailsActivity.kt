package com.test.todolist.notedetails.view

import android.os.Bundle
import com.test.todolist.R
import com.test.todolist.base.BaseActivity
import com.test.todolist.notedetails.presentation.NoteDetailsPresenter
import kotlinx.android.synthetic.main.activity_note_details.*
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.android.synthetic.main.activity_notes_list.toolbar

class NoteDetailsActivity: BaseActivity<NoteDetailsPresenter, NoteDetailsTarget>(), NoteDetailsTarget {

    override fun getViewResourceId(): Int = R.layout.activity_note_details

    companion object {
        const val OPENED_NOTE_ID_KEY = "note_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pass noteId to presenter
        presenter.onCreate(intent.getIntExtra(OPENED_NOTE_ID_KEY, 0))

        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_todo_details)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun setTitle(title: String) {
        tv_note_title?.text = title
    }

    override fun setDueOn(dueOn: String) {
        tv_note_due_on?.text = dueOn
    }

    override fun setId(id: String) {
        tv_note_id?.text = id
    }

    override fun setUserId(userId: String) {
        tv_note_user_id?.text = userId
    }

    override fun setStatus(status: String) {
        tv_note_status?.text = status
    }
}