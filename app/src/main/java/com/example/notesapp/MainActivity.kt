package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                adapter.updateList(it)
            }
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.title} Deleted!", Toast.LENGTH_SHORT).show()
    }

    fun addNoteData(view: View) {
        val title = findViewById<EditText>(R.id.etTitle)
        val disc = findViewById<EditText>(R.id.etDisc)
        val noteTitle = title.text.toString()
        val noteDisc = disc.text.toString()
        if (noteTitle.isNotEmpty() && noteDisc.isNotEmpty()){
            viewModel.addNote(Note(noteTitle, noteDisc))
            Toast.makeText(this,"$noteTitle Added!", Toast.LENGTH_SHORT).show()
            title.setText("");
            disc.setText("");
        }

    }

}