package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task title", "task content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assertions.assertEquals(1L, task.getId());
        Assertions.assertEquals("task title", task.getTitle());
        Assertions.assertEquals("task content", task.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "task title", "task content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assertions.assertEquals(1L, taskDto.getId());
        Assertions.assertEquals("task title", taskDto.getTitle());
        Assertions.assertEquals("task content", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task taskOne = new Task();
        taskOne.setId(1L);
        taskOne.setTitle("task one");
        taskOne.setContent("task one content");
        Task taskTwo = new Task();
        taskTwo.setId(2L);
        taskTwo.setTitle("task two");
        taskTwo.setContent("task two content");
        Task taskThree = new Task();
        taskThree.setId(3L);
        taskThree.setTitle("task three");
        taskThree.setContent("task three content");
        Task taskFour = new Task();
        taskFour.setId(4L);
        taskFour.setTitle("task four");
        taskFour.setContent("task four content");

        tasks.add(taskOne);
        tasks.add(taskTwo);
        tasks.add(taskThree);
        tasks.add(taskFour);

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        //Then
        Assertions.assertEquals(4, taskDtos.size());
        Assertions.assertEquals(3L, taskDtos.get(2).getId());
        Assertions.assertEquals("task two content", taskDtos.get(1).getContent());
        Assertions.assertEquals("task two", taskDtos.get(1).getTitle());
        Assertions.assertFalse(taskDtos.isEmpty());
    }
}
