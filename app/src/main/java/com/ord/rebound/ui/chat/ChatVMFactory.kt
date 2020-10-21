package com.ord.rebound.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ord.rebound.data.repository.ChatRepository

class ChatVMFactory(private val chatRepository: ChatRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ChatViewModel(chatRepository) as T
}