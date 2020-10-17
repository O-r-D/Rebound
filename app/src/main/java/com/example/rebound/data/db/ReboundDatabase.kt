package com.example.rebound.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rebound.data.model.Message
import com.example.rebound.util.Constants


@Database(entities = [Message::class], version = 1)
abstract class ReboundDatabase() : RoomDatabase() {

    abstract fun chatDao(): ChatDao

    companion object {
        private var instance: ReboundDatabase? = null

        operator fun invoke(context: Context): ReboundDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context,
                ReboundDatabase::class.java,
                Constants.REBOUND_DB
            ).allowMainThreadQueries().build().also {
                instance = it
            }
        }


    }
}