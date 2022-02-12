package com.crud.tasks.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminConfigTest {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    void shouldReturnAdminMailTest() {
        //Given

        //When
        String mail = adminConfig.getAdminMail();

        //Then
        Assertions.assertEquals("kodilla.course.lukas.marchel@gmail.com", mail);
    }
}
