package com.example.myapplication.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication.data.application.MyApplication
import com.example.myapplication.data.entity.NewsEntity

class HomeViewModel(application : Application) :AndroidViewModel(application) {
    private val newsDAO = (application as MyApplication).database.newsDAO()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val allNews: LiveData<List<NewsEntity>> = newsDAO.getAllNews().asLiveData()
}