package com.test.todolist.notedetails.presentation

import com.test.todolist.base.BasePresenter
import com.test.todolist.notedetails.view.NoteDetailsTarget
import com.test.todolist.noteslist.data.Note
import com.test.todolist.noteslist.data.NotesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


class NoteDetailsPresenter @Inject constructor(private val notesRepository: NotesRepository) :
    BasePresenter<NoteDetailsTarget>() {

    lateinit var note: Note

    /*
    * Calls from NoteDetailsActivity and receives noteId parameter
    * to load note data from DB.
    * */
    fun onCreate(noteId: Int) {
        addSubscription(
            notesRepository.getNote(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { note ->
                    initNote(note)
                }
        )
    }

    /*
    * Send note content to display in activity
    * */
    private fun initNote(note: Note?) {
        note?.let {
            this.note = note
            target?.apply {
                setTitle(note.title ?: "Unknown")
                setDueOn(note.getAdaptedDueOnDate())
                setId(note.id.toString())
                setUserId(note.userId?.toString() ?: "Unknown")
                setStatus(note.status?.capitalize(Locale.ENGLISH) ?: "Unknown")
            }
        }
    }
}