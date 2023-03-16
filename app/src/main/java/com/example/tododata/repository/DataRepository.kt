package com.example.tododata.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Update
import com.example.tododata.room.DataEntity
import com.example.tododata.room.TodoDao

class DataRepository (private val DataDao: TodoDao) {

    // on below line we are creating a variable for our list
    // and we are getting all the notes from our DAO class.

    val allData: LiveData<List<DataEntity>> = DataDao.getTodoData()

    // on below line we are creating an insert method
    // for adding the note to our database.
    suspend fun insert(note: DataEntity) {
        DataDao.insert(note)
    }
    // on below line we are creating a delete method
    // for deleting our note from database.
    suspend fun delete(note: DataEntity){
        DataDao.delete(note)
    }

    // on below line we are creating a update method for
    // updating our note from database.
    suspend fun update(note: DataEntity){
        DataDao.update(note)
    }
}