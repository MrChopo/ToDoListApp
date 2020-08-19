package com.example.todolistapp.data

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE userId = userId")
    fun getUserById(userId: Long): User

    @Update
    fun upgradeUser(user: User)

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}