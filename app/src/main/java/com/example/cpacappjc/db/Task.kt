package com.example.cpacappjc.db

import java.util.*

/* TODO: Use this date format

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date).toString();
 */

data class Task(
    val id: UUID = UUID.randomUUID(),
    var parentList: String,
    var title: String,
    var desc: String? = null,
    var date: String? = null,
    var timestamp: String,
    var completed: Boolean = false,
    var completedTimestamp: String? = null
)