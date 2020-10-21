package com.ord.rebound.util

import com.ord.rebound.data.repository.ChatRepository
import com.ord.rebound.data.repository.UserRepository
import com.ord.rebound.ui.chat.ChatVMFactory
import com.ord.rebound.ui.home.UserVMFactory

object Injector {

    fun provideChatVMFactory() = ChatVMFactory(ChatRepository())

    fun provideUserVMFactory() = UserVMFactory(UserRepository())
}