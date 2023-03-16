package com.example.tododata.view.activites

import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tododata.R
import com.example.tododata.databinding.ActivityMainBinding
import com.example.tododata.viewmodel.DataViewModel
import com.example.tododata.room.DataEntity
import com.example.tododata.view.adapter.DataAdapter
import com.example.tododata.view.adapter.DataClickInterface
import com.example.tododata.view.adapter.TodoClickDeleteInterface

class MainActivity : AppCompatActivity(), DataClickInterface, TodoClickDeleteInterface {

    // on below line we are creating a variable
    // for our recycler view, mainActivity and viewModel.

    lateinit var viewModal: DataViewModel
    lateinit var todoRV: RecyclerView
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // on below line we are setting layout
        // manager to our recycler view.
        todoRV = findViewById(R.id.RecyclerView)
        todoRV.layoutManager = LinearLayoutManager(this)

        // on below line we are initializing our adapter class.
        val noteRVAdapter = DataAdapter(this, this, this)

        // on below line we are setting
        // adapter to our recycler view.
        todoRV.adapter = noteRVAdapter

        // on below line we are
        // initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DataViewModel::class.java)

        // on below line we are calling getAll notes method
        // from our view modal class to observer the changes on list.

        viewModal.getallData.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })

        binding.btnAdd.setOnClickListener {
            // adding a click listener for  addButton
            // and opening a new intent to add a new note.
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
                //finish()
        }
    }

    override fun onDeleteIconClick(note: DataEntity) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Data")
        //set message for alert dialog
        builder.setMessage("Do You Want To Delete ")
        builder.setIcon(R.drawable.alert_dial)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            // method from our view modal to delete our not.
            viewModal.deleteNote(note)
            // displaying a toast message
            Toast.makeText(this, "${note.title} Deleted", Toast.LENGTH_LONG).show()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(
                applicationContext,
                "clicked cancel\n operation cancel",
                Toast.LENGTH_LONG
            ).show()
        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_LONG).show()
        }
        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.setOnShowListener(object :OnShowListener{
            override fun onShow(p0: DialogInterface?) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED)
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN)

            }

        })
        alertDialog.show()
    }





    override fun onNoteClick(data: DataEntity) {
        // opening a new intent and passing a data to it.

        val intent = Intent(this@MainActivity, AddActivity::class.java)
        intent.putExtra("todoType", "Edit")
        intent.putExtra("todoTitle",data.title)
        intent.putExtra("todoDesc",data.desc)
        intent.putExtra("todoDate",data.date)
        intent.putExtra("todoId", data.id)
        startActivity(intent)


    }
}