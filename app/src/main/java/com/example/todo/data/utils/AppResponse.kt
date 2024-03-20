package com.example.todo.data.utils

sealed class AppResponse<out T>{
    class Success<out T>(val data: T) : AppResponse<T>()
    class Failure<out T>(val msg: Throwable) : AppResponse<T>()
    data object Loading : AppResponse<Nothing>()
}
