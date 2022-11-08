package com.example.cpacappjc.components

import com.example.cpacappjc.db.Task

data class TaskListPagerContent(
    val title: String,
    val tasks: List<Task>,
)

fun getTaskLists(): List<TaskListPagerContent> {

    val taskList1 = listOf(Task(
        parentList = "Hi",
        title = "Hello",
        timestamp = "123456",
    ))

    val taskList2 = listOf(
        Task(
            parentList = "Hi",
            title = "Hello",
            timestamp = "123456",
        ),
        Task(
            parentList = "Hi",
            title = "Hola",
            timestamp = "654321",
        )
    )

    return listOf(
        TaskListPagerContent("List1", taskList1),
        TaskListPagerContent("List2", taskList2)
    )
}