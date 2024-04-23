package com.example.todo.data.repository

import com.example.todo.data.models.UserModel
import com.example.todo.data.services.AuthService
import com.example.todo.data.utils.AppResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepo @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthService {

    val currentUser = auth.currentUser
    override fun loginWithEmail(email: String, password: String): Flow<AppResponse<String>> =
        callbackFlow {
            trySend(AppResponse.Loading)
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        trySend(AppResponse.Success("success"))
                    }.addOnFailureListener {
                        trySend(AppResponse.Failure(it))
                    }
            } catch (e: Exception) {
                trySend(AppResponse.Failure(e))
            }
            awaitClose { close() }
        }

    override fun createWithEmail(
        username: String,
        email: String,
        password: String
    ): Flow<AppResponse<String>> =
        callbackFlow {

            trySend(AppResponse.Loading)
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        trySend(AppResponse.Success("success"))
                        db.collection("user").document(it?.user?.uid!!)
                    }.addOnFailureListener {
                        trySend(AppResponse.Failure(it))
                    }
            } catch (e: Exception) {
                trySend(AppResponse.Failure(e))
            }
            awaitClose { close() }
        }

}
