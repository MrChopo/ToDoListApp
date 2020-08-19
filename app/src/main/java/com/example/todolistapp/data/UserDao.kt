package com.example.todolistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE userId = userId")
    fun getUserById(userId: Long): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}