package com.example.magicbooks.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.magicbooks.pojo.BookDetail
import com.example.magicbooks.pojo.BookResponseItem


@Database(entities = [BookResponseItem::class, BookDetail::class], version = 1)
abstract class BookDataBase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDataBase? = null

        fun getDataBase(context: Context): BookDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDataBase::class.java,
                        "bookDao"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
