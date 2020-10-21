package com.ord.rebound.ui.chat

import androidx.lifecycle.ViewModel
import com.ord.rebound.data.model.Chat
import com.ord.rebound.data.repository.ChatRepository

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {

    fun getChat(firstUserId: String, secondUserId: String) =
        repository.getChat(firstUserId, secondUserId)

    fun insertChat(chat: Chat) = repository.insertChat(chat)

}