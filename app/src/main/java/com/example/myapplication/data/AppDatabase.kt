package com.example.myapplication.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.myapplication.data.application.MyApplication
import com.example.myapplication.data.dao.NewsDao
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entity.NewsEntity
import com.example.myapplication.data.entity.UserEntity

@Database(
    entities = [NewsEntity::class, UserEntity::class],
    version = 1,
//    autoMigrations = [
//        AutoMigration( from = 1, to = 2)
//    ],
//    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun userDao(): UserDao

    //数据库单例
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
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