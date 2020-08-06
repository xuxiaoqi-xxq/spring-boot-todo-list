package com.oocl.todo.integration;

import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_all_todos_when_get_todo_endpoints_given_nothing() throws Exception {
        //given
        Todo todo1 = new Todo(null, "todo1", false);
        todoRepository.save(todo1);
        Todo todo2 = new Todo(null, "todo2", false);
        todoRepository.save(todo2);

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].content").value("todo1"))
                .andExpect(jsonPath("$[0].status").value(false));
    }

    @Test
    void should_return_created_todo_when_post_todo_endpoints_given_todo() throws Exception {
        //given
        String todo = "{\"id\":1,\"content\":\"todo1\",\"status\":false}";

        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(todo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("todo1"))
                .andExpect(jsonPath("$.status").value(false));

        Todo savedTodo = todoRepository.findAll().get(0);
        assertEquals("todo1", savedTodo.getContent());
        assertFalse(savedTodo.getStatus());
    }

    @Test
    void should_return_updated_todo_when_put_todo_endpoints_given_todo() throws Exception {
        //given
        Todo savedTodo = todoRepository.save(new Todo(1, "todo1", false));
        String todo = "{\"id\":" + savedTodo.getId() + ",\"content\":\"todo1\",\"status\":true}";

        mockMvc.perform(put("/todos/" + savedTodo.getId()).contentType(MediaType.APPLICATION_JSON).content(todo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("todo1"))
                .andExpect(jsonPath("$.status").value(true));

        Todo repositoryTodo = todoRepository.findById(savedTodo.getId()).orElse(null);
        assertEquals("todo1", repositoryTodo.getContent());
        assertTrue(repositoryTodo.getStatus());
    }

    @Test
    void should_return_nothing_when_delete_todo_endpoints_given_todo_id() throws Exception {
        //given
        Todo savedTodo = todoRepository.save(new Todo(null, "todo1", false));

        mockMvc.perform(delete("/todos/" + savedTodo.getId()))
                .andExpect(status().isOk());

        Todo repositoryTodo = todoRepository.findById(savedTodo.getId()).orElse(null);
        assertNull(repositoryTodo);
    }
}
