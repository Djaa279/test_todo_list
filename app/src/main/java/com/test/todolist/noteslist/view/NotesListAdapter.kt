package com.test.todolist.noteslist.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.todolist.R
import com.test.todolist.noteslist.data.Note

class NotesListAdapter (
    private val itemClickListener: (Note) -> Unit
) : RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>() {

    private val mDiffer = AsyncListDiffer(this, NoteDiffUtilsCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_item, parent, false)
        return NotesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(mDiffer.currentList[position], itemClickListener)
    }

    fun addNotes(notes: List<Note>) {
        val newList = mDiffer.currentList + notes
        mDiffer.submitList(newList)
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var noteTitleTextView: TextView = itemView.findViewById(R.id.tv_note_title)

        fun bind(
            note: Note,
            clickListener: (Note) -> Unit
        ) {
            noteTitleTextView.text = note.title
            itemView.setOnClickListener { clickListener(note) }
        }
    }
}

/*
* Support class that is useful when adapter deals with data changes
* */
class NoteDiffUtilsCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}
