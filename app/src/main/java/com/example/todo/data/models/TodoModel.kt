package com.example.todo.data.models

import com.google.type.DateTime
import java.time.LocalDateTime

data class TodoModel(
    val id: String = "",
    val todo: String = "",
    val description: String = "",
    val color: Int = 0,
    val date: String = "",
)
