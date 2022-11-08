package com.example.cpacappjc.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cpacappjc.db.Task

@Composable
fun TasksContent(tasks: List<Task>) {
    val rTasks = remember { tasks }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp)
    ) {
        items(
            items = rTasks,
            itemContent = {
                TaskItem(task = it)
            }
        )
    }
}

@Composable
fun TaskItem(task: Task) {
    Row {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = task.title, style = typography.h6)
            Text(text = task.timestamp, style = typography.subtitle1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestTasksContent() {
    TasksContent(tasks = getTaskLists()[1])
}