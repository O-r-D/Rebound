package com.example.rebound.util

import com.example.rebound.data.repository.ChatRepository
import com.example.rebound.data.repository.UserRepository
import com.example.rebound.ui.chat.ChatVMFactory
import com.example.rebound.ui.home.UserVMFactory

object Injector {

    fun provideChatVMFactory() = ChatVMFactory(ChatRepository())

    fun provideUserVMFactory() = UserVMFactory(UserRepository())
}