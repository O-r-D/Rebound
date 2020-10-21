package com.ord.rebound.ui.home

import androidx.lifecycle.ViewModel
import com.ord.rebound.data.model.User
import com.ord.rebound.data.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun getUsers() = repository.getUsers()

    fun getUser(uid: String) = repository.getUser(uid)

    fun insertUsers(users: List<User>) = repository.insertUsers(users)
}