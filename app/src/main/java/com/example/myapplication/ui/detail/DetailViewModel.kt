package com.example.myapplication.ui.detail

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.application.MyApplication
import com.example.myapplication.utils.tools
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _author = MutableLiveData<String>()
    private val _title = MutableLiveData<String>()
    private val _avatarUri = MutableLiveData<Uri>()
    private val _content = MutableLiveData<String>()
    private val _headImageUri = MutableLiveData<Uri>()
    private val _thumbnailImageUri = MutableLiveData<Uri>()
    private val newsDAO = (application as MyApplication).database.newsDAO()


    val author: LiveData<String> = _author
    val title: LiveData<String> = _title
    val avatarUri: LiveData<Uri> = _avatarUri
    val content: LiveData<String> = _content


    fun getNewsById(id: Int) = viewModelScope.launch {
        val newsEntity = newsDAO.getNewsById(id)
        _author.value = newsEntity.author
        _title.value = newsEntity.title
        _avatarUri.value =
            Uri.parse("android.resource://com.example.myapplication/${R.drawable.icons8_avatar_48}")
                ?: Uri.parse(newsEntity.avatarUri)
        _headImageUri.value = Uri.parse(newsEntity.headImageUri)
        _thumbnailImageUri.value = Uri.parse(newsEntity.thumbnailImageUri)
        _content.value = newsEntity.content
    }
//    init {
//        _avatarUri.value = Uri.parse("android.resource://com.example.myapplication/${R.drawable.icons8_avatar_48}")
//        _title.value = "Test Title"
//        _author.value = "Heleyang"
//        _content.value = tools.randomString(10000)
//    }
}