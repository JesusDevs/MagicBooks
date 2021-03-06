package com.example.magicbooks.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookdetail")
data class BookDetail(
        @PrimaryKey
        @SerializedName("id")
        val id: Int = 1,
        @SerializedName("author")
        val author: String = "Chinua Achebe",
        @SerializedName("country")
        val country: String = "Nigeria",
        @SerializedName("imageLink")
        val imageLink: String = "https://user-images.githubusercontent.com/22780253/103938792-90279200-5109-11eb-906a-50ac3b73e40d.jpg",
        @SerializedName("language")
        val language: String = "English",
        @SerializedName("link")
        val link: String = "https://en.wikipedia.org/wiki/Things_Fall_Apart",
        @SerializedName("pages")
        val pages: Int = 209,
        @SerializedName("title")
        val title: String = "Things Fall Apart",
        @SerializedName("year")
        val year: Int = 1958,
        @SerializedName("price")
        val price: Int = 12500,
        @SerializedName("lastPrice")
        val lastPrice: Int = 17500,
        @SerializedName("delivery")
        val delivery: Boolean = true
)