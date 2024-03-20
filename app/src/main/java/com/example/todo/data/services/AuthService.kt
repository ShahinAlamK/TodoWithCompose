package com.example.todo.data.services

import com.example.todo.data.utils.AppResponse
import kotlinx.coroutines.flow.Flow

interface AuthService {

    fun loginWithEmail(email:String,password:String):Flow<AppResponse<String>>
    fun createWithEmail(username:String,email:String,password:String):Flow<AppResponse<String>>

}