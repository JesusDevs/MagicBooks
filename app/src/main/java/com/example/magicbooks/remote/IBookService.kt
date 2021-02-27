package com.example.magicbooks.remote

import com.example.magicbooks.pojo.BookResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface IBookService {
    @GET("books")
    suspend fun getGames(): Response<List<BookResponseItem>>

    //metodo para el detalle
    //@GET("detail")
    //suspend fun getDetail(): Response<DetailBookId>

}