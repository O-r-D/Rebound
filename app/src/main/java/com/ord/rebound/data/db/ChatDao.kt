package com.ord.rebound.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ord.rebound.data.model.Chat

@Dao
interface ChatDao {

    @Query("SELECT * FROM chats WHERE firstUserId = :firstUserId AND secondUserId = :secondUserId")
    fun getChat(firstUserId: Long, secondUserId: Long): LiveData<Chat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(chat: Chat)
}