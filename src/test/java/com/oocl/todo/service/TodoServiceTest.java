package com.oocl.todo.service;

import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Test
    void should_return_updated_todo_when_put_given_todo() {
        //given
        TodoRepository todoRepository = mock(TodoRepository.class);
        Todo newTodo = new Todo(1, "todo content", true);
        when(todoRepository.findById(1)).thenReturn(Optional.of(new Todo(1, "todo content", false)));
        when(todoRepository.save(newTodo)).thenReturn(newTodo);
        TodoService todoService = new TodoService(todoRepository);

        //when
        Todo updatedTodo = todoService.update(1, newTodo);

        //then
        assertEquals(newTodo, updatedTodo);
    }
}
