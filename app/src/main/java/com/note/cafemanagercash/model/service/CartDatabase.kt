package com.note.cafemanagercash.model.service

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class CartDatabase : RoomDatabase() {
    abstract fun itemtDao(): ItemsDao

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getDatabase(context: Context): CartDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "cart_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}