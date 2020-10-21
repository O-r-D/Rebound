package com.ord.rebound.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ord.rebound.util.Constants


//@Database(entities = [Chat::class, User::class], version = 1)
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