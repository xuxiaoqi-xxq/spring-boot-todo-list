package com.oocl.todo.service;

import com.oocl.todo.exception.NoSuchDataException;
import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo add(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(Integer id, Todo newTodo) throws NoSuchDataException {
        Todo oldTodo = todoRepository.findById(id).orElse(null);
        if (oldTodo == null) {
            throw new NoSuchDataException();
        }
        oldTodo.setStatus(newTodo.getStatus());
        return todoRepository.save(oldTodo);
    }

    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }
}
