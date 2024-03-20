package com.example.todo.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.repository.AuthRepo
import com.example.todo.data.utils.AppResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthVideModel @Inject constructor(private val authRepo: AuthRepo) : ViewModel() {

    val error: MutableState<String> = mutableStateOf("")
    val state: MutableState<Boolean> = mutableStateOf(false)

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading


    suspend fun loginWithEmail(email: String, passwords: String)= viewModelScope.launch {
        authRepo.loginWithEmail(email, passwords).collect { snapshot ->
            when (snapshot) {
                AppResponse.Loading -> {
                    _isLoading.value = true
                }

                is AppResponse.Failure -> {
                    error.value = snapshot.msg.toString()
                    _isLoading.value = false
                    Log.d("Auth", snapshot.msg.toString())
                }

                is AppResponse.Success -> {
                    _isLoading.value = false
                    state.value = true
                }
            }

        }
    }


    suspend fun createWithEmail(username:String,email: String, passwords: String)= viewModelScope.launch {
        authRepo.createWithEmail(username,email, passwords).collect { snapshot ->
            when (snapshot) {
                AppResponse.Loading -> {
                    _isLoading.value = true
                }
                is AppResponse.Failure -> {
                    error.value = snapshot.msg.toString()
                    _isLoading.value = false
                    Log.d("Auth", snapshot.msg.toString())
                }

                is AppResponse.Success -> {
                    _isLoading.value = false
                    state.value = true
                }
            }

        }
    }
}