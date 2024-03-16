package com.example.springstart.service;

import com.example.springstart.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();

    Todo getTodoById(int id);

    void addTodo(Todo todo);

    void editTodo(Todo todo);

    void deleteTodoById(int id);

    List<Todo> searchTodosByTask(String task);

    List<Todo> searchTodosByIsDone(boolean isDone);

    List<Todo> searchTodosByTaskAndIsDone(String task, boolean isDone);

    List<Todo> searchTodos(String task, String isDone);
}
