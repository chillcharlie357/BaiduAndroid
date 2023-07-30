package com.example.myapplication.data.application

import android.app.Application
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { NewsRepository(database.newsDAO()) }

}