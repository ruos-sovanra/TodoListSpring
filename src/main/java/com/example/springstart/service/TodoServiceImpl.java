package com.example.springstart.service;

import com.example.springstart.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{
    private final List<Todo> todoList;

    public TodoServiceImpl(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @Override
    public Todo getTodoById(int id) {
        return todoList.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void addTodo(Todo todo) {
        todoList.add(todo);
    }

    @Override
    public void editTodo(Todo todo) {
        Todo existingTodo = todoList.stream().filter(t -> t.getId() == todo.getId()).findFirst().orElse(null);
        if (existingTodo != null) {
            existingTodo.setTask(todo.getTask());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setDone(todo.isDone());
        }
    }

    @Override
    public void deleteTodoById(int id) {
        todoList.removeIf(todo -> todo.getId() == id);
    }

    public List<Todo> searchTodosByTask(String task) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByIsDone(boolean isDone) {
        return todoList.stream()
                .filter(todo -> todo.isDone() == isDone)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByTaskAndIsDone(String task, boolean isDone) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task) && todo.isDone() == isDone)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodos(String task, String isDone) {
        if (task != null && !task.isEmpty()) {
            Boolean isDoneValue = null;
            if ("complete".equalsIgnoreCase(isDone)) {
                isDoneValue = true;
            } else if ("pending".equalsIgnoreCase(isDone)) {
                isDoneValue = false;
            }

            if (isDoneValue != null) {
                return searchTodosByTaskAndIsDone(task, isDoneValue);
            } else {
                return searchTodosByTask(task);
            }
        } else {
            if ("complete".equalsIgnoreCase(isDone)) {
                return searchTodosByIsDone(true);
            } else if ("pending".equalsIgnoreCase(isDone)) {
                return searchTodosByIsDone(false);
            } else {
                return getAllTodos();
            }
        }
    }
}
