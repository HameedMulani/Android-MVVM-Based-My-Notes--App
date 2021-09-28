package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>(){

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textTitleView: TextView = itemView.findViewById(R.id.tvNoteTittle)
        val textDiscView: TextView = itemView.findViewById(R.id.tvNoteDisc)
        val deleteButton: ImageView = itemView.findViewById(R.id.ivDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNoteTitle = allNotes[position]
        holder.textTitleView.text = currentNoteTitle.title
        holder.textDiscView.text = currentNoteTitle.disc
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

//    for New List Adding in Database
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

// onclick Listener for recyclerView
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}