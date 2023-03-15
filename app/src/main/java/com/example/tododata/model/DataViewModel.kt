package com.example.tododata.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
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
    val allNotes : LiveData<List<DataEntity>>



    init {
        val dao = MyDatabase.getDatabase(application).dataDao()
        repository = DataRepository(dao)
        allNotes = repository.allNotes
    }


    fun deleteNote (note: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }


    fun updateNote(note: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }



    fun addNote(note: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}