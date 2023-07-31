package com.example.myapplication.data.repository

import androidx.annotation.WorkerThread
import com.example.myapplication.data.dao.NewsDao
import com.example.myapplication.data.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val newsDAO: NewsDao) {
    val allNews: Flow<List<NewsEntity>> = newsDAO.getAllNews()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertNews(newsEntity: NewsEntity){
        newsDAO.insert(newsEntity)
    }
}