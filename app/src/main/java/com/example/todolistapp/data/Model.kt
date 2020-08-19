package com.example.todolistapp.data

import android.provider.ContactsContract
import androidx.room.*
import com.google.type.DateTime
import java.io.FileDescriptor

@Entity(tableName = "users")
data class User(@PrimaryKey val userId: Int,
                      @ColumnInfo(name = "nick_name") val nickName: String?,
                      @ColumnInfo(name = "email") val email: ContactsContract.CommonDataKinds.Email?)

@Entity(tableName = "tasks")
data class Task(@PrimaryKey val taskId: Int,
                    @ColumnInfo(name = "description") val description: String?,
                    @ColumnInfo(name = "done") val done: Boolean = false,
                    @ColumnInfo(name = "change_time") val changeTime: DateTime?,
                    @ColumnInfo(name = "userId") val userId: Long)

@Entity(primaryKeys = ["userId", "taskId"])
data class UserTaskListCrossRef(
    val userId: Long,
    val taskId: Long
)

data class UserWithTask(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "taskId",
        associateBy = Junction(UserTaskListCrossRef::class)
    )
    val tasks: List<Task>
)