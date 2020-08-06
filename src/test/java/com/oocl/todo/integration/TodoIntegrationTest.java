package com.oocl.todo.integration;

import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void should_return_all_todos_when_get_todo_endpoints_given_nothing() throws Exception {
        //given
        List<Todo> todos = asList(new Todo(1, "todo1", false), new Todo(2, "todo2", false));
        todoRepository.saveAll(todos);

        mockMvc.perform(get("/todos"))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].content").value("todo1"))
                .andExpect(jsonPath("$[0].status").value(false));
    }

    @Test
    void should_return_created_todo_when_post_todo_endpoints_given_todo() throws Exception {
        //given
        String todo = "{\"id\":1,\"content\":\"todo1\",\"status\":false}";

        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(todo))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("todo1"))
                .andExpect(jsonPath("$.status").value(false));

        Todo savedTodo = todoRepository.findAll().get(0);
        assertEquals("todo1", savedTodo.getContent());
        assertFalse(false);
    }
}