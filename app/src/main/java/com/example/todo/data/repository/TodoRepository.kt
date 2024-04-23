package com.example.todo.data.repository

import com.example.todo.data.models.TodoModel
import com.example.todo.data.services.TodoService
import com.example.todo.data.utils.AppResponse
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val db: FirebaseFirestore) : TodoService {
    override fun insertTodo(todoModel: TodoModel): Flow<AppResponse<String>> = callbackFlow {

        trySend(AppResponse.Loading)
        db.collection("Todos").add(todoModel)
            .addOnSuccessListener {
                trySend(AppResponse.Success("Successfully Insert"))
            }
            .addOnFailureListener {
                trySend(AppResponse.Failure(it))
            }

        awaitClose { close() }
    }

    init {
        fetchTodo()
    }

    override fun fetchTodo(): Flow<AppResponse<List<TodoModel>>> = callbackFlow {

        trySend(AppResponse.Loading)
        db.collection("Todos").get()
            .addOnSuccessListener {
                val data = it.map { snap ->
                    snap.toObject(TodoModel::class.java)
                }
                trySend(AppResponse.Success(data))

            }
            .addOnFailureListener {
                trySend(AppResponse.Failure(it))
            }
        awaitClose { close() }
    }

    override fun update(id: String): Flow<AppResponse<TodoModel>> {
        TODO("Not yet implemented")
    }

    override fun delete(id: String): Flow<AppResponse<TodoModel>> {
        TODO("Not yet implemented")
    }
}