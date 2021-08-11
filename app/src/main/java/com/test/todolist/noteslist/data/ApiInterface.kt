package com.test.todolist.noteslist.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("todos")
    fun getNotes(): Single<NotesAPIResponse>

    @GET("product")
    fun getProduct(@Query("id") limit: Int): Single<Note>
}