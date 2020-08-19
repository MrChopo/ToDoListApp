package com.example.todolistapp.ui.activity.tasklist

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.R
import com.example.todolistapp.databinding.ActivityTaskListBinding
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.android.synthetic.main.add_task_dialog.view.*

class TaskListActivity : AppCompatActivity() {

    lateinit var model: TaskListViewModel
    private lateinit var binding: ActivityTaskListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        model = ViewModelProvider(this).get(TaskListViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_list)
        binding.lifecycleOwner = this
        binding.taskListViewModel = model

        observeViewModel(model)

    }

    private fun observeViewModel(model: TaskListViewModel) {
        model.showDialog.observe(this, Observer {
            //Toast.makeText(this, "Тест", Toast.LENGTH_LONG).show()
            if (it){
                model.clearShowDialog()
                dialogAddTask()
            }
        })

    }

    private fun dialogAddTask(){

        val layoutInflaterAndroid = LayoutInflater.from(applicationContext)
        val view: View = layoutInflaterAndroid.inflate(R.layout.add_task_dialog, null)
        val alertDialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        alertDialogBuilder.setView(view)

        alertDialogBuilder.setTitle("Add new task").setCancelable(false).setPositiveButton("Save") { _, _ -> }
            .setNegativeButton("Cancel") { _, _ -> }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
            .setOnClickListener(View.OnClickListener {
                if (view.dialogTaskDescription.text.isEmpty()) {
                    Toast.makeText(this, "Enter task", Toast.LENGTH_SHORT)
                        .show()
                    return@OnClickListener
                } else {
                    // save new task model.saveNewTask(task)
                    alertDialog.dismiss()
                }
            })

    }
}