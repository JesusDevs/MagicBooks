package com.example.magicbooks.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.magicbooks.pojo.BookDetail
import com.example.magicbooks.pojo.BookResponseItem

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllBooks(list: List<BookResponseItem>)

    @Query("SELECT * FROM book_free")
    fun getAllBookDataBase(): LiveData<List<BookResponseItem>>

    @Query("SELECT * FROM book_free WHERE id = :id")
    fun getBookByID(id: String): LiveData<BookResponseItem>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneBookDetails(detail: BookDetail)


    @Query("SELECT * FROM  bookdetail WHERE id=:id")
    fun getOneBookDetails(id: Int): LiveData<BookDetail>

}