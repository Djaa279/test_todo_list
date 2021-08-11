package com.test.todolist.noteslist.data

import android.annotation.SuppressLint
import com.test.todolist.utils.ApiSchedulerTransformer
import io.reactivex.Single
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val notesDAO: NotesDAO
) {
    companion object {
        const val PAGE_LIMIT = 10
    }

    /*
    * Retrieve list of notes. First it will try to fetch data
    * from API and save it in DB.
    * In case with no Internet Connection
    * it will try to fetch list from DB.
    * In case when there is no data in DB
    * it will throw an exception that should be
    * caught in 'onError' method.
    * */
    fun getNotes(offset: Int): Single<List<Note>> {
        return getApiNotes()
            .onErrorResumeNext {
                notesDAO.getNotes().map {
                    if (it.isEmpty()) {
                        throw NoInternetException()
                    }
                    it
                }
            }.compose(ApiSchedulerTransformer())
    }

    /*
    * Get note from DB by ID
    * */
    fun getNote(id: Int): Single<Note?> = notesDAO.getNote(id)

    /*
    * Retrieve data from server and persist it in local DB
    * */
    private fun getApiNotes(): Single<List<Note>> {
        return apiInterface.getNotes().map {
            notesDAO.insertAllNotes(it.data ?: listOf())
            it.data
        }
    }

    /*
    * Retrieve only database items
    * */
    fun getDatabaseNotes(): Single<List<Note>> {
        return notesDAO.getNotes()
            .compose(ApiSchedulerTransformer())
    }
}

/*
* Simple exception
* */
class NoInternetException : Throwable()
