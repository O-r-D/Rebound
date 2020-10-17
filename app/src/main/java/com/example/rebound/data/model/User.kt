package com.example.rebound.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @PrimaryKey(autoGenerate = true) private val uid: Long,
    val username: String,
    val lastMessage: Message
)