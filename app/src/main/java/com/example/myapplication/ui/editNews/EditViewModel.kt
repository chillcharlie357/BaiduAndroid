package com.example.myapplication.ui.editNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.data.entity.NewsEntity
import com.example.myapplication.data.repository.NewsRepository
import kotlinx.coroutines.launch

class EditViewModel (private val repository: NewsRepository) : ViewModel(){
    val allNews: LiveData<List<NewsEntity>> = repository.allNews.asLiveData()

    fun insertNews(newsEntity: NewsEntity) = viewModelScope.launch {
        repository.insertNews(newsEntity)
    }
}

class EditViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(EditViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return EditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}