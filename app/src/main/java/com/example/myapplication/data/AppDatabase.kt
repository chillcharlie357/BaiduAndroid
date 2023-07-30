package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.NewsDAO
import com.example.myapplication.data.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun newsDAO(): NewsDAO

    //数据库单例
    companion object {
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}