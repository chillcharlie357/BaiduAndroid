package com.example.myapplication.ui.Detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.utils.tools

class DetailViewModel : ViewModel() {
    private val _author = MutableLiveData<String>()
    private val _title = MutableLiveData<String>()
    private val _avatarId = MutableLiveData<Int>()
    private val _post = MutableLiveData<String>()

    val author: LiveData<String> = _author
    val title : LiveData<String> = _title
    val  avatar_id : LiveData<Int> = _avatarId
    val  post :LiveData<String> = _post

    init {
        _avatarId.value = R.drawable.icons8_avatar_48
        _title.value = "Test Title"
        _author.value = "Heleyang"
        _post.value = tools.randomString(10000)
    }
}