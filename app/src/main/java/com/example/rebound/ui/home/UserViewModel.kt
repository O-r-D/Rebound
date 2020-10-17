package com.example.rebound.ui.home

import com.example.rebound.data.model.User
import com.example.rebound.data.repository.UserRepository

class UserViewModel internal constructor(private val repository: UserRepository) {

    fun getUsers() = repository.getUsers()

    fun getUser(uid: Long) = repository.getUser(uid)

    fun insertUsers(users: List<User>) = repository.insertUsers(users)
}