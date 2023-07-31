package com.example.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val password : String,
    val user_id : String,
    var user_name : String = "default name",
)