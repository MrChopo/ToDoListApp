package com.example.todolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}