package com.example.myapplication.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.R

@Entity(tableName = "News")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val author: String,
    val content: String,
    val avatarUri: String,
    val headImageUri: String? = null,
    val thumbnailImageUri: String? = null
)