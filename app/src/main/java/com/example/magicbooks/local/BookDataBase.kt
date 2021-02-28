package com.example.magicbooks.local

import android.content.Context
import android.content.LocusId
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.magicbooks.pojo.BookResponseItem
import com.example.magicbooks.pojo.DetailBookRes


@Database(entities = [BookResponseItem::class, DetailBookRes::class], version = 1)
abstract class BookDataBase : RoomDatabase() {

    abstract fun getBookDao(): BookDao
    abstract fun getBookDetails(id: String): BookDetailDao

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
                    "gameDao"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
