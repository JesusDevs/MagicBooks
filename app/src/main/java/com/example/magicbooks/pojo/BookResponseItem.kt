package com.example.magicbooks.pojo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "book_free")
data class BookResponseItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 11,
    @SerializedName("author")
    val author: String = "Giovanni Boccaccio",
    @SerializedName("country")
    val country: String = "Italy",
    @SerializedName("imageLink")
    val imageLink: String = "https://user-images.githubusercontent.com/22780253/103941896-4a20fd00-510e-11eb-9d9c-a211f97596b3.jpg",
    @SerializedName("language")
    val language: String = "Italian",
    @SerializedName("title")
    val title: String = "The Decameron"
)