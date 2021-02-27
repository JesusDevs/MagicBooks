package com.example.magicbooks.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBook {

    companion object {
        val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/anchorBooks/"
        fun getRetrofitInstance(): IBookService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(IBookService::class.java)
        }
    }
}