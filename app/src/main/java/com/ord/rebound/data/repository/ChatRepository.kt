package com.ord.rebound.data.repository

import com.ord.rebound.data.db.ChatDao
import com.ord.rebound.data.model.Chat

class ChatRepository private constructor(private val chatDao: ChatDao) {

    fun getChat(firstUserId: String, secondUserId: String) =
        chatDao.getChat(firstUserId, secondUserId)

    fun insertChat(chat: Chat) = chatDao.insertChat(chat)


    companion object {
        private var instance: ChatRepository? = null

        operator fun invoke(chatDao: ChatDao): ChatRepository = instance ?: synchronized(this) {
            instance ?: ChatRepository(chatDao).also {
                instance = it
            }
        }
    }
}