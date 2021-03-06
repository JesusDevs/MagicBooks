package com.example.magicbooks.remote

import com.example.magicbooks.pojo.BookDetail
import com.example.magicbooks.pojo.BookResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IBookService {
    @GET("books")
    suspend fun getBookAllItem(): Response<List<BookResponseItem>>

    @GET("bookDetail/{id}")
    suspend fun getBookDetail(@Path("id") id: Int): Response<BookDetail>

}