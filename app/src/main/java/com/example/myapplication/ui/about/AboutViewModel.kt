package com.example.myapplication.ui.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.application.MyApplication
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.utils.Failure
import com.example.myapplication.utils.Result
import com.example.myapplication.utils.Success
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class AboutViewModel(application: Application) : AndroidViewModel(application) {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin
    private val userDao: UserDao = (application as MyApplication).database.userDao()

    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    init {
        _isLogin.value = false
    }

    suspend fun login(userId: String, password: String): Result {
        val userEntity = userDao.getUserByUserId(userId)
        if (userEntity == null || userEntity.password != password) {
            _isLogin.value = false
            return Failure("$userId not exist or wrong password")
        }

        _userName.value = userEntity.user_name
        _isLogin.value = true
        return Success("$userId login")
    }

    fun signOut(): Result {
        _isLogin.value = false
        return Success("sign out")
    }

    suspend fun signUp(userId: String, password: String): Result {
        val userEntity = userDao.getUserByUserId(userId)
        if (userEntity != null) {
            return Failure("$userId exist")
        }
        userDao.insertUser(
            UserEntity(
                user_id = userId,
                password = password
            )
        )
        return Success("$userId sign up")
    }

    suspend fun getUserInfo(userId: String): UserEntity {
        return userDao.getUserByUserId(userId)!!
    }

    suspend fun updateUserName(userId: String, newName: String): Result {
        if (newName.isEmpty()) return Failure("empty new name")
        _userName.value = newName

        val userEntity = userDao.getUserByUserId(userId) ?: return Failure("no such user")
        userEntity.user_name = newName
        userDao.updateUser(userEntity)
        return Success("updated")
    }

}