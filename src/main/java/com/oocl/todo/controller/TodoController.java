package com.oocl.todo.controller;

import com.oocl.todo.dto.TodoResponse;
import com.oocl.todo.mapper.TodoResponseMapper;
import com.oocl.todo.model.Todo;
import com.oocl.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoResponseMapper todoResponseMapper;

    @GetMapping
    public List<TodoResponse> getTodo() {
        List<Todo> todos = todoService.findAll();
        return todos.stream().map(todoResponseMapper::from).collect(Collectors.toList());
    }
}
