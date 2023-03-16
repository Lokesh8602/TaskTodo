package com.example.tododata.view.activites


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tododata.R
import com.example.tododata.databinding.ActivityAddBinding
import com.example.tododata.viewmodel.DataViewModel
import com.example.tododata.room.DataEntity
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    lateinit var viewModal: DataViewModel

    var updateID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DataViewModel::class.java)

        binding.etDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    binding.etDate?.setText("$mDay/${mMonth + 1}/$mYear")

                },
                year,
                month,
                day
            )
            datePicker.show()
        }

        val noteType = intent.getStringExtra("todoType")
        if (noteType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val updateTitle = intent.getStringExtra("todoTitle")
            val updateDescription = intent.getStringExtra("todoDescription")
            val updatedate = intent.getStringExtra("desc")
            updateID = intent.getIntExtra("todoId", -1)
            binding.btnSave.setText("New Data Update ")
            binding.tvAddTask.setText("UPDATE DATA")
            binding.etTitle.setText(updateTitle)
            binding.etDate.setText(updateDescription)
            binding.etdesc.setText(updatedate)
        } else {
            binding.btnSave.setText("Save Note")
        }



        binding.btnSave.setOnClickListener {

            val todoTitle = binding.etTitle.text.toString()
            val todoDate = binding.etDate.text.toString()
            val tododesc = binding.etdesc.text.toString()
            if (noteType.equals("Edit")) {
                if (todoTitle.isNotEmpty() && todoDate.isNotEmpty()) {

                    val updatedNote = DataEntity( todoTitle, tododesc,todoDate)
                    updatedNote.id = updateID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Data Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (todoTitle.isNotEmpty() && todoDate.isNotEmpty()) {
                    viewModal.addNote(DataEntity( todoTitle,tododesc,todoDate))
                    Toast.makeText(this, "$todoTitle Added", Toast.LENGTH_LONG).show()
                }
            }
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    this.finish()
        }
    }
}
