package com.example.tododata.room
import androidx.lifecycle.LiveData
import androidx.room.*




// annotation for dao class.
@Dao
interface TodoDao{

    // below is the insert method for
    // adding a new entry to our database.
@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insert(note :DataEntity)

    // below is the delete method
    // for deleting our todoData.
@Delete
suspend fun delete(note: DataEntity)

    /*below is the method to read all the todoData
      from our database we have specified the query for it.
     .*/

@Query("Select * from notesTable order by id DESC ")
fun getTodoData(): LiveData<List<DataEntity>>


    // below method is use to update the note.
@Update
suspend fun update(note: DataEntity)



}