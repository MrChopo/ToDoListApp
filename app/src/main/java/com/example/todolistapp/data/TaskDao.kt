package com.example.todolistapp.data

import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE userId IN (:userIds)")
    fun loadAllByUserId(userIds: IntArray): List<Task>

    @Update
    fun upgradeTask(task: Task)

    @Insert
    fun insertAll(vararg tasks: List<Any>)

    @Delete
    fun delete(task: Task)
}