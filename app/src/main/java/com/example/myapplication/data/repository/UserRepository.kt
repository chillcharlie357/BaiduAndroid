package com.example.myapplication.data.repository

import androidx.annotation.WorkerThread
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val allUsers: Flow<List<UserEntity>>  = userDao.getAllUsers()

    @WorkerThread
    suspend fun insertUser(userEntity: UserEntity){
        userDao.insertUser(userEntity)
    }
}