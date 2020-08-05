package com.oocl.todo.service;

import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoServiceTest {
    @Test
    void should_return_all_todos_when_get_given_nothing() {
        //given
        TodoRepository todoRepository = mock(TodoRepository.class);
        List<Todo> repositoryTodos = Collections.singletonList(new Todo(1, "todo content", false));
        when(todoRepository.findAll()).thenReturn(repositoryTodos);
        TodoService todoService = new TodoService(todoRepository);

        //when
        List<Todo> foundTodos = todoService.findAll();

        //then
        assertEquals(repositoryTodos, foundTodos);
    }

    @Test
    void should_return_add_todo_when_post_given_todo() {
        //given
        TodoRepository todoRepository = mock(TodoRepository.class);
        Todo repositoryTodo = new Todo(1, "todo content", false);
        when(todoRepository.save(repositoryTodo)).thenReturn(repositoryTodo);
        TodoService todoService = new TodoService(todoRepository);

        //when
        Todo savedTodo = todoService.add(repositoryTodo);

        //then
        assertEquals(repositoryTodo, savedTodo);
    }


}
