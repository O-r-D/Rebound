package com.ord.rebound.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey val uid: String,
    val firstUserId: String,
    val secondUserId: String,
    val messages: ArrayList<Message>
)