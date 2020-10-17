package com.example.rebound.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rebound.data.repository.ChatRepository

class ChatVMFactory(private val chatRepository: ChatRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ChatViewModel(chatRepository) as T
}