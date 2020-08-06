package com.oocl.todo.controller;

import com.oocl.todo.dto.TodoRequest;
import com.oocl.todo.dto.TodoResponse;
import com.oocl.todo.mapper.RequestTodoMapper;
import com.oocl.todo.mapper.TodoResponseMapper;
import com.oocl.todo.model.Todo;
import com.oocl.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoResponseMapper todoResponseMapper;
    @Autowired
    private RequestTodoMapper todoRequestMapper;

    @GetMapping
    public List<TodoResponse> getTodo() {
        List<Todo> todos = todoService.findAll();
        return todos.stream().map(todoResponseMapper::from).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodo(@RequestBody TodoRequest todoRequest) {
        Todo todo = todoRequestMapper.to(todoRequest);
        return todoResponseMapper.from(todoService.add(todo));
    }
}
