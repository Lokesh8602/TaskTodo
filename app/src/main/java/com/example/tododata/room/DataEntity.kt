package com.example.tododata.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// on below line we are specifying our table name
 @Entity(tableName = "notesTable" )


// on below line we are specifying our column info
// and inside that we are passing our column name
data class DataEntity(

    @ColumnInfo(name ="title")
    var title: String,

    @ColumnInfo(name = "desc")
    var desc : String,

    @ColumnInfo(name = "date")
    var date: String)

// on below line we are specifying our key and
// then auto generate as true and we are
// specifying its initial value as 0

   {
    @PrimaryKey( autoGenerate = true)
    var id:Int=0
    }






