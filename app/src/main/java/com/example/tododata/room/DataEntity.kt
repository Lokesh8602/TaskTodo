package com.example.tododata.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable" )

data class DataEntity(
    @PrimaryKey( autoGenerate = true)
    var id:Int,
    @ColumnInfo(name ="title")
    var title: String,
    @ColumnInfo(name = "date")
    var date: String





)
