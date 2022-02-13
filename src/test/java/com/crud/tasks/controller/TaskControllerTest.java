package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1L, "task one title", "task content"));
        taskDtos.add(new TaskDto(2L, "task two title", "task content"));
        taskDtos.add(new TaskDto(3L, "task three title", "task content"));
        taskDtos.add(new TaskDto(4L, "task four title", "task content"));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "task one title", "task content"));
        tasks.add(new Task(2L, "task two title", "task content"));
        tasks.add(new Task(3L, "task three title", "task content"));
        tasks.add(new Task(4L, "task four title", "task content"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));

    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task one title", "task content");
        Task task = new Task(1L, "task one title", "task content");

        when(dbService.getTask(taskDto.getId())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("task one title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("task content")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task one title", "task content");
        Task task = new Task(1L, "task one title", "task content");

        when(dbService.getTask(taskDto.getId())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask")
                        .param("taskId","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "task one title", "task content");
        TaskDto taskDto = new TaskDto(1L, "task one title", "task content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(gson.toJson(taskDto)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("task one title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("task content")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task one title", "task content");
        Task task = new Task(1L, "task one title", "task content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(gson.toJson(taskDto)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
