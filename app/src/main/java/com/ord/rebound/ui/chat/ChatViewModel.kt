package com.ord.rebound.ui.chat

import androidx.lifecycle.ViewModel
import com.ord.rebound.data.model.Chat
import com.ord.rebound.data.repository.ChatRepository

class ChatViewModel internal constructor(private val repository: ChatRepository) : ViewModel() {

    fun getChat(firstUserId: Long, secondUserId: Long) =
        repository.getChat(firstUserId, secondUserId)

    fun insertChat(chat: Chat) = repository.insertChat(chat)

}