package com.example.todo.data.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.repository.AuthRepo
import com.example.todo.data.utils.AppResponse
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthVideModel @Inject constructor(
    private val application: Application,
    private val authRepo: AuthRepo,
    private val db : FirebaseFirestore
) : ViewModel() {

    private var _res : MutableState<AuthState> = mutableStateOf(AuthState())
    val res: State<AuthState> = _res


    suspend fun loginWithEmail(email: String, passwords: String)= viewModelScope.launch {
        authRepo.loginWithEmail(email, passwords).collect { snapshot ->
            when (snapshot) {
                AppResponse.Loading -> {
                    _res.value = AuthState(loading = true)
                }

                is AppResponse.Failure -> {
                    _res.value = AuthState(error = snapshot.msg.toString())
                }

                is AppResponse.Success -> {
                    _res.value = AuthState(data = true)
                }
            }

        }
    }


    suspend fun createWithEmail(username:String,email: String, passwords: String)= viewModelScope.launch {
        authRepo.createWithEmail(username,email, passwords).collect { snapshot ->
            when (snapshot) {
                AppResponse.Loading -> {
                    _res.value = AuthState(loading = true)
                }
                is AppResponse.Failure -> {
                    Toast.makeText(application, snapshot.msg.toString(), Toast.LENGTH_SHORT).show()
                    _res.value = AuthState(error = snapshot.msg.toString())
                }

                is AppResponse.Success -> {
                    authRepo.currentUser?.uid?.let {
                        db.collection("Users").document(it).set(
                            mapOf(
                                "uid" to authRepo.currentUser.uid,
                                "username" to username,
                                "email" to email
                            )
                        )
                    }
                    _res.value = AuthState(data = true)
                }
            }

        }
    }
}

data class AuthState(
    val data : Boolean = false,
    val error: String = "",
    val loading : Boolean = false
)