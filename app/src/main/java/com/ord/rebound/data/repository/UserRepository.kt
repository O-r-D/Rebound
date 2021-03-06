package com.ord.rebound.data.repository

import com.ord.rebound.data.db.UserDao
import com.ord.rebound.data.model.User

class UserRepository private constructor(private val userDao: UserDao) {

    fun getUsers() = userDao.getUsers()

    fun getUser(uid: String) = userDao.getUser(uid)

    fun insertUsers(users: List<User>) = userDao.insertUsers(users)

    fun insertUser(user: User) = userDao.inserUser(user)

    companion object {
        private var instance: UserRepository? = null

        operator fun invoke(userDao: UserDao): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(userDao).also { instance = it }
        }
    }
}