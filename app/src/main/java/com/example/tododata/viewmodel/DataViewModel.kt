package com.example.tododata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tododata.repository.DataRepository
import com.example.tododata.room.DataEntity
import com.example.tododata.room.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel (application: Application) : AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository

    val repository: DataRepository
    val getallData : LiveData<List<DataEntity>>

    init {
        val dao = MyDatabase.getDatabase(application).dataDao()
        repository = DataRepository(dao)
        getallData = repository.allData
    }

    // on below line we are creating a new method for deleting a todoData. In this we are
    // calling a delete method from our repository to delete our todoData.
    fun deleteNote (note: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    // on below line we are creating a new method for updating a todoData. In this we are
    // calling a update method from our repository to update our todoData.
    fun updateNote(note: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}