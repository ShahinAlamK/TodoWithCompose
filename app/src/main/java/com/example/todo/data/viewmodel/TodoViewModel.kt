package com.example.todo.data.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.models.TodoModel
import com.example.todo.data.repository.TodoRepository
import com.example.todo.data.utils.AppResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {


    private val _res: MutableState<TodoState> = mutableStateOf(TodoState())
    val res: State<TodoState> = _res


    fun insertTodo(todoModel: TodoModel) = todoRepository.insertTodo(todoModel)

    init {
        fetchTodo()
    }
    fun fetchTodo() = viewModelScope.launch {

        todoRepository.fetchTodo().collect {

            when (it) {
                is AppResponse.Loading -> {
                    _res.value = TodoState(isLoading = true)
                }

                is AppResponse.Failure -> {
                    _res.value = TodoState(msg = it.msg.toString())
                }

                is AppResponse.Success -> {
                    _res.value = TodoState(todoList = it.data)
                }
            }
        }
    }
}

data class TodoState(
    val todoList: List<TodoModel> = emptyList(),
    val msg: String = "",
    val isLoading: Boolean = false
)