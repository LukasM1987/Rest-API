package com.crud.tasks.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskDtoTest {

    @Test
    void taskWithNoArgsConstructorTest() {
        //Given
        Task task = new Task();

        //When

        //Then
        Assertions.assertNull(task.getId());
        Assertions.assertNull(task.getTitle());
        Assertions.assertNull(task.getContent());

    }

    @Test
    void taskWithAllArgsConstructorTest() {
        //Given
        Task task = new Task("task one", "task content");

        //When

        //Then
        Assertions.assertEquals("task one", task.getTitle());
        Assertions.assertEquals("task content", task.getContent());
    }

    @Test
    void taskWithSettersTest() {
        //Given
        Task task = new Task("task one", "task content");

        //When
        task.setTitle("new task one");
        task.setContent("new task content");

        //Then
        Assertions.assertEquals("new task one", task.getTitle());
        Assertions.assertEquals("new task content", task.getContent());
    }
}
