package com.ord.rebound.util

import android.content.Context
import com.ord.rebound.data.db.ReboundDatabase
import com.ord.rebound.data.repository.ChatRepository
import com.ord.rebound.data.repository.UserRepository
import com.ord.rebound.ui.chat.ChatVMFactory
import com.ord.rebound.ui.home.UserVMFactory

object Injector {

    fun provideChatVMFactory(context: Context) =
        ChatVMFactory(ChatRepository(ReboundDatabase(context).chatDao()))

    fun provideUserVMFactory(context: Context) =
        UserVMFactory(UserRepository(ReboundDatabase(context).userDao()))
}