package com.ord.rebound.ui.home

import com.ord.rebound.data.model.User
import com.ord.rebound.data.repository.UserRepository

class UserViewModel internal constructor(private val repository: UserRepository) {

    fun getUsers() = repository.getUsers()

    fun getUser(uid: Long) = repository.getUser(uid)

    fun insertUsers(users: List<User>) = repository.insertUsers(users)
}