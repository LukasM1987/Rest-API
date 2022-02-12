package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void getAllTasksTest() {
        //Given
        Task taskOne = new Task("task one", "task content");
        Task taskTwo = new Task("task two", "task content");
        Task taskThree = new Task("task three", "task content");
        Task taskFour = new Task("task four", "task content");

        dbService.saveTask(taskOne);
        dbService.saveTask(taskTwo);
        dbService.saveTask(taskThree);
        dbService.saveTask(taskFour);

        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        Assertions.assertEquals(4, tasks.size());

        //Clean up
        dbService.deleteTask(taskOne.getId());
        dbService.deleteTask(taskTwo.getId());
        dbService.deleteTask(taskThree.getId());
        dbService.deleteTask(taskFour.getId());
    }

    @Test
    void getTaskTest() {
        //Given
        Task taskOne = new Task("task one", "task content");
        Task taskTwo = new Task("task two", "task content");
        Task taskThree = new Task("task three", "task content");
        Task taskFour = new Task("task four", "task content");

        dbService.saveTask(taskOne);
        dbService.saveTask(taskTwo);
        dbService.saveTask(taskThree);
        dbService.saveTask(taskFour);

        //When
        Optional<Task> tasks = dbService.getTask(taskTwo.getId());

        //Then
        Assertions.assertEquals("task two", tasks.get().getTitle());

        //Clean up
        dbService.deleteTask(taskOne.getId());
        dbService.deleteTask(taskTwo.getId());
        dbService.deleteTask(taskThree.getId());
        dbService.deleteTask(taskFour.getId());
    }

    @Test
    void saveTaskTest() {
        //Given
        Task task = new Task("task title", "content");

        //When
        Task taskService = dbService.saveTask(task);

        //Then
        Assertions.assertNotNull(taskService);
        Assertions.assertEquals("task title", taskService.getTitle());
        Assertions.assertEquals("content", taskService.getContent());

        //Clean up
        dbService.deleteTask(task.getId());
    }
}
