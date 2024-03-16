package com.example.springstart.controller;

import com.example.springstart.model.Todo;
import com.example.springstart.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String getTodoList(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todo-list";
    }

    @GetMapping("/{id}")
    public String getTodoById(@PathVariable int id, Model model) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            model.addAttribute("todo", todo);
            return "todo-details";
        } else {
            return "redirect:/todo";
        }
    }

    @GetMapping("/new")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "add-todo";
    }

    @PostMapping("/new")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/todo";
    }

    @GetMapping("/edit/{id}")
    public String showEditTodoForm(@PathVariable int id, Model model) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            model.addAttribute("todo", todo);
            return "edit-todo";
        } else {
            return "redirect:/todo";
        }
    }

    @PostMapping("/edit")
    public String editTodo(@ModelAttribute Todo todo) {
        todoService.editTodo(todo);
        return "redirect:/todo";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoById(@PathVariable int id) {
        todoService.deleteTodoById(id);
        return "redirect:/todo";
    }

    @GetMapping("/search")
    public String searchTodoList(@RequestParam(required = false) String task,
                                 @RequestParam(required = false) String isDone,
                                 Model model) {
        List<Todo> searchResults = todoService.searchTodos(task, isDone);
        model.addAttribute("todos", searchResults);
        return "todo-list";
    }
}
