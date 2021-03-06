package com.example.magicbooks.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.magicbooks.local.BookDao
import com.example.magicbooks.pojo.BookDetail
import com.example.magicbooks.pojo.BookResponseItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookRepository(private val dao: BookDao) {
    private val myRetrofit = RetrofitBook.getRetrofitInstance()
    val liveDataBookDB: LiveData<List<BookResponseItem>> = dao.getAllBookDataBase()

    suspend fun getBookWithCorutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitBook.getRetrofitInstance().getBookAllItem()

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

    fun getBookDetails(id: Int): LiveData<BookDetail> {
        return dao.getOneBookDetails(id)
    }

    fun getBookFromApi(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { myRetrofit.getBookDetail(id) }
        service.onSuccess {
            when (it.code()) {

                in 200..299 -> it.body()?.let { details ->
                    dao.insertOneBookDetails(details)
                    Log.e("detail", it.toString())
                }
                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }

        }
        service.onFailure {

            Log.e("ERROR", it.message.toString())
        }
    }
}