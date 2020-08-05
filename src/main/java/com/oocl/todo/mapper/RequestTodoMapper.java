package com.oocl.todo.mapper;

import com.oocl.todo.dto.TodoRequest;
import com.oocl.todo.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestTodoMapper {
    public TodoRequest from(Todo todo) {
        TodoRequest todoRequest = new TodoRequest();
        BeanUtils.copyProperties(todo, todoRequest);
        return todoRequest;
    }

    public Todo to(TodoRequest todoRequest) {
        return null;
    }
}
