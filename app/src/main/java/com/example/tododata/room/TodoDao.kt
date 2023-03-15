package com.example.tododata.room
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao{


@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insert(note :DataEntity)


@Delete
suspend fun delete(note: DataEntity)


@Query("Select * from notesTable order by id DESC ")
fun getAllNotes(): LiveData<List<DataEntity>>

@Update
suspend fun update(note: DataEntity)



}