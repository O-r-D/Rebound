package com.example.rebound.data.repository

import com.example.rebound.data.db.UserDao
import com.example.rebound.data.model.User

class UserRepository private constructor(private val userDao: UserDao) {

    fun getUsers() = userDao.getUsers()

    fun getUser(uid: Long) = userDao.getUser(uid)

    fun insertUsers(users: List<User>) = userDao.insertUsers(users)

    companion object {
        private var instance: UserRepository? = null

        operator fun invoke(): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository().also { instance = it }
        }
    }
}