package com.ord.rebound.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ord.rebound.data.model.Chat
import com.ord.rebound.data.model.User
import com.ord.rebound.util.Constants

@TypeConverters(Converter::class)
@Database(entities = [Chat::class, User::class], version = 1)
abstract class ReboundDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
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