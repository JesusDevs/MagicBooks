package com.example.magicbooks.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.magicbooks.local.BookDataBase
import com.example.magicbooks.pojo.BookResponseItem
import com.example.magicbooks.remote.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookRepository
    val bookLiveDataFromDataBase: LiveData<List<BookResponseItem>>

    init {
        val dao = BookDataBase.getDataBase(application).getBookDao()
        repository = BookRepository(dao)
        viewModelScope.launch {
            repository.getBookWithCorutines()
        }
        bookLiveDataFromDataBase = repository.liveDataBookDB
    }

    fun getBookByID(id: String): LiveData<BookResponseItem> {
        return repository.getBookByID(id)
    }

}