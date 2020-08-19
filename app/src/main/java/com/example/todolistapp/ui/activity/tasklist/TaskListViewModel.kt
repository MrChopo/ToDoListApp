package com.example.todolistapp.ui.activity.tasklist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskListViewModel : ViewModel() {

    private val showDialogMutable: MutableLiveData<Boolean> = MutableLiveData()
    val showDialog: LiveData<Boolean> = showDialogMutable

    fun showDialogAddNewTask(){
        showDialogMutable.postValue(true)
    }

    fun clearShowDialog(){
        showDialogMutable.postValue(false)
    }
}