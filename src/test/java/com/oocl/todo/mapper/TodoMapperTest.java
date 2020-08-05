package com.oocl.todo.mapper;

import com.oocl.todo.dto.TodoRequest;
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
}
