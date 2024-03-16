package com.example.springstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Data
@AllArgsConstructor
public class Todo {
    private int nextId = 1;
    private int id;
    private String task;
    private String description;
    private boolean isDone;
    private LocalDate createdAt;
    public Todo() {
        this.createdAt = LocalDate.now();
        this.id = nextId++;
    }
}
