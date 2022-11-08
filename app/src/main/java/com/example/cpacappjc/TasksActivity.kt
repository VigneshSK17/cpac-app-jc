package com.example.cpacappjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cpacappjc.components.TaskListPagerContent
import com.example.cpacappjc.components.getTaskLists
import com.example.cpacappjc.ui.theme.CpacAppJCTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TasksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CpacAppJCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TaskListPagerScreen()
                }
            }
        }
    }
}

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TaskListPagerScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val items = getTaskLists()
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = items[currentPage].title,
                    style = MaterialTheme.typography.h2
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = items[currentPage].tasks.map { task -> task.title }.joinToString(),
                    style = MaterialTheme.typography.h4
                )
            }

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