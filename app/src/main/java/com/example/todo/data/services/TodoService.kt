package com.example.todo.data.services

import com.example.todo.data.models.TodoModel
import com.example.todo.data.utils.AppResponse
import kotlinx.coroutines.flow.Flow

interface TodoService {

    fun insertTodo(todoModel: TodoModel): Flow<AppResponse<String>>

    fun fetchTodo(): Flow<AppResponse<List<TodoModel>>>

    fun update(id: String): Flow<AppResponse<TodoModel>>

    fun delete(id: String): Flow<AppResponse<TodoModel>>
}