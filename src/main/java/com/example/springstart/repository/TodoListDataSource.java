package com.example.springstart.repository;

import com.example.springstart.model.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;
@Configuration
public class TodoListDataSource {
    @Bean
    public List<Todo> todoList() {
        List<Todo> todos = new ArrayList<>();
        // Add more todos if needed
        return todos;
    }
}
