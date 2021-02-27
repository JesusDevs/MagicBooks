package com.example.magicbooks.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.magicbooks.pojo.BookResponseItem

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllBooks(list: List<BookResponseItem>)

    @Query("SELECT * FROM book_free")
    fun getAllBookDataBase(): LiveData<List<BookResponseItem>>

    @Query("SELECT * FROM book_free WHERE id = :id")
    fun getBookByID(id: String): LiveData<BookResponseItem>

//metodo para obtener detalle de libros por id
    // @Query("Select * from detail_book where id = :id")
    //fun getGameDetails(id:String): LiveData<DetailsBookId>
}