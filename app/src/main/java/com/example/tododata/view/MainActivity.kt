package com.example.tododata.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tododata.R
import com.example.tododata.databinding.ActivityMainBinding
import com.example.tododata.model.DataViewModel
import com.example.tododata.room.DataEntity
import com.example.tododata.view.adapter.DataAdapter
import com.example.tododata.view.adapter.NoteClickDeleteInterface
import com.example.tododata.view.adapter.NoteClickInterface

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface  {

    lateinit var viewModal: DataViewModel
    lateinit var todoRV: RecyclerView

    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoRV = findViewById(R.id.RecyclerView)



        todoRV.layoutManager = LinearLayoutManager(this)


        val noteRVAdapter = DataAdapter(this, this, this)


        todoRV.adapter = noteRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DataViewModel::class.java)


        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })

        binding.btnAdd.setOnClickListener {

            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onDeleteIconClick(note: DataEntity) {
        viewModal.deleteNote(note)
        // displaying a toast message
        Toast.makeText(this, "${note.title} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: DataEntity) {
        val intent = Intent(this@MainActivity, AddActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDescription", note.date)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }
}