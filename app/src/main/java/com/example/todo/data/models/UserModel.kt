package com.example.todo.data.models

data class UserModel(
    val uid:String = "",
    val username:String = "",
    val email:String = "",
    val profile:String = ""
){
    fun toMap() = mapOf(
        "uid" to uid,
        "username" to username,
        "email" to email,
        "profile" to profile
    )
}
