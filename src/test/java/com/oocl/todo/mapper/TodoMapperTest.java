package com.oocl.todo.mapper;

import com.oocl.todo.dto.TodoRequest;
import com.oocl.todo.dto.TodoResponse;
import com.oocl.todo.model.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoMapperTest {

    @Test
    void should_return_request_todo_when_from_request_given_todo() {
        //given
        RequestTodoMapper requestTodoMapper = new RequestTodoMapper();
        Todo todo = new Todo(1, "todo content", false);

        //when
        TodoRequest todoRequest = requestTodoMapper.from(todo);

        //then
        assertEquals(1, todoRequest.getId());
        assertEquals("todo content", todoRequest.getContent());
        assertFalse(todoRequest.getStatus());
    }

    @Test
    void should_return_todo_when_to_todo_given_request_todo() {
        //given
        RequestTodoMapper requestTodoMapper = new RequestTodoMapper();
        TodoRequest todoRequest = new TodoRequest(1, "todo content", false);

        //when
        Todo todo = requestTodoMapper.to(todoRequest);

        //then
        assertEquals(1, todo.getId());
        assertEquals("todo content", todo.getContent());
        assertFalse(todo.getStatus());
    }

    @Test
    void should_return_response_todo_when_from_response_given_todo() {
        //given
        TodoResponseMapper todoResponseMapper = new TodoResponseMapper();
        Todo todo = new Todo(1, "todo content", false);

        //when
        TodoResponse todoResponse = todoResponseMapper.from(todo);

        //then
        assertEquals(1, todoResponse.getId());
        assertEquals("todo content", todoResponse.getContent());
        assertFalse(todoResponse.getStatus());
    }

    @Test
    void should_return_todo_when_to_todo_given_esponse_todo() {
        //given
        TodoResponseMapper todoResponseMapper = new TodoResponseMapper();
        TodoResponse todoResponse = new TodoResponse(1, "todo content", false);

        //when
        Todo todo = todoResponseMapper.to(todoResponse);

        //then
        assertEquals(1, todo.getId());
        assertEquals("todo content", todo.getContent());
        assertFalse(todo.getStatus());
    }
}
