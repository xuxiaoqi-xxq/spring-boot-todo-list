package com.oocl.todo.controller;

import com.oocl.todo.dto.TodoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @GetMapping
    public TodoResponse getTodo() {
        return null;
    }
}
