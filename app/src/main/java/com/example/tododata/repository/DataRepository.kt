package com.example.tododata.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Update
import com.example.tododata.room.DataEntity
import com.example.tododata.room.TodoDao

class DataRepository (private val notesDao: TodoDao) {


    val allNotes: LiveData<List<DataEntity>> = notesDao.getAllNotes()

    suspend fun insert(note: DataEntity) {
        notesDao.insert(note)
    }

    suspend fun delete(note: DataEntity){
        notesDao.delete(note)
    }
    suspend fun update(note: DataEntity){
        notesDao.update(note)
    }
}