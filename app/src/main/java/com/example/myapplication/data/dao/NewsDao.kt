package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    
    @Insert
    suspend fun insert(newsEntity: NewsEntity)

    @Query("SELECT * FROM News")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM News WHERE id = :newsId")
    suspend fun getNewsById(newsId: Int) : NewsEntity
}