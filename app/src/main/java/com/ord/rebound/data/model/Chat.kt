package com.ord.rebound.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey(autoGenerate = true) private val uid: Long,
    val firstUserId: Long,
    val secondUserId: Long,
    val messages: List<Message>
)