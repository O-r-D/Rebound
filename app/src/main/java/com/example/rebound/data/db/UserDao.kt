package com.example.rebound.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rebound.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<User>>


    @Query("SELECT * FROM users WHERE uid = :uid")
    fun getUser(uid: Long): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)


}