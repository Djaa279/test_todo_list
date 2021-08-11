package com.test.todolist.noteslist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat
import java.time.DateTimeException

@Entity(
    tableName = "notes"
)
data class Note(

    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    val userId: Int?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("due_on")
    @ColumnInfo(name = "due_on")
    val due_on: String?,

    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String?,

) {

    /*
    * Format dueOn date into readable string format
    * */
    fun getAdaptedDueOnDate(): String {
        var adaptedDate = due_on ?: "Unknown"
        try {
            val outputFormat = "yyyy-MM-dd HH:mm:ss"

            val dtf = ISODateTimeFormat.dateTime()
            val parsedDate: LocalDateTime = dtf.parseLocalDateTime(due_on)

            adaptedDate = parsedDate.toString(DateTimeFormat.forPattern(outputFormat))
        } catch (e: DateTimeException) {
            e.printStackTrace()
        }

        return adaptedDate
    }

}