package com.example.magicbooks.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.magicbooks.local.BookDao
import com.example.magicbooks.pojo.BookResponseItem

class BookRepository(private val dao: BookDao) {

    val liveDataBookDB: LiveData<List<BookResponseItem>> = dao.getAllBookDataBase()

    suspend fun getBookWithCorutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitBook.getRetrofitInstance().getGames()

            when (response.isSuccessful) {

                true -> response.body()?.let {
                    //Aca se esta insertando en la Base de datos
                    Log.d("repo1", "${it}")
                    dao.insertAllBooks(it)
                    Log.d("repo", "${it}")
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()} ")
            }
        } catch (t: Throwable) {
            Log.e("ERROR CORUTINA", t.message.toString())
        }
    }

    fun getBookByID(id: String): LiveData<BookResponseItem> {
        return dao.getBookByID(id)
    }

}