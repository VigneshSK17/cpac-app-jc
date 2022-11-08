package com.example.cpacappjc.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cpacappjc.db.Task

// TODO: Add methods which actually modify the task list from firebase

@Composable
fun TasksContent(index: Int) {
    val mTasks = remember { mutableStateListOf<Task>() }
    mTasks.addAll(getTaskLists()[index])

    Column (modifier = Modifier.fillMaxHeight()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(mTasks) { i, task ->
                val t = task.copy()

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) { Checkbox(
                        checked = task.completed,
                        onCheckedChange = {
                            t.completed = it
                            mTasks[i] = t
                        }
                    )
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                    Text(text = task.title, style = typography.h6)
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        mTasks.remove(t)
                    }) {
                        Icon(Icons.Filled.Delete, "Delete this task")
                    }
                }

            }
        }
        Button(onClick = {
            mTasks.add(Task(
                parentList = mTasks[0].parentList,
                title = "Hola",
                timestamp = "987654"
            ))
        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
            Icon(Icons.Filled.Add, "Add a new task")
            Text(text = "Add New Task", style = typography.button)
        }
    }

}