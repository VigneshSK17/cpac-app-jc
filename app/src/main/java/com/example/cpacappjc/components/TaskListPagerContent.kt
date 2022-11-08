package com.example.cpacappjc.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cpacappjc.db.Task
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class TaskListPagerContent(
    val title: String,
    val tasks: List<Task>,
)

fun getTaskLists(): List<List<Task>> {

    val taskList1 = listOf(Task(
        parentList = "List1",
        title = "Hello",
        timestamp = "123456",
    ))

    val taskList2 = listOf(
        Task(
            parentList = "List2",
            title = "Hello",
            timestamp = "123456",
        ),
        Task(
            parentList = "List2",
            title = "Hola",
            timestamp = "654321",
            completed = true
        )
    )

    return listOf(taskList1, taskList2)
}

fun getTaskListPagerContent(): List<TaskListPagerContent> {

    val taskLists = getTaskLists()

    return listOf(
        TaskListPagerContent("List1", taskLists[0]),
        TaskListPagerContent("List2", taskLists[1])
    )
}

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TaskListPagerScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val items = getTaskListPagerContent()
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        TaskListTabs(
            items = items,
            pagerState = pagerState,
            scope = coroutineScope
        )

        HorizontalPager(
            count = items.size,
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { currentPage ->
            TasksContent(currentPage)
        }

//        Button(
//            onClick = {
//                coroutineScope.launch {
//                    pagerState.animateScrollToPage(page = 1)
//                }
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "Scroll to second page")
//        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TaskListTabs(
    items: List<TaskListPagerContent>,
    pagerState: PagerState,
    scope: CoroutineScope
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        items.forEachIndexed { i, item ->
            Tab(
                selected = pagerState.currentPage == i,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(page = i)
                    }
                },
                selectedContentColor = MaterialTheme.colors.primary
            ) {
                Text(text = item.title)
            }
        }
    }
}
