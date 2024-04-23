package com.example.todo.data.di

import com.example.todo.data.repository.TodoRepository
import com.example.todo.data.services.TodoService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun providesRepo(todoRepository: TodoRepository): TodoService
}