package com.akash.e_learniverse_spring_boot;

import com.akash.e_learniverse_spring_boot.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * "@SpringBootTest" is used to Start the "Spring Boot Container" which will create the Beans for the "Test" Environment
 */

//@RunWith(SpringRunner.class) --> Not Needed
@SpringBootTest
public class HelloServiceTests {

    private HelloService helloService;

    @Before
    public void setUp() {
        helloService = new HelloService();
    }

    @Test
    public void testGreetWithName() {
        String result = helloService.greet("Ramos");

        assertEquals("Hola, Ramos!", result);

        assertTrue(helloService.greet("Ramos").equals("Hola, Ramos!"));

        assertNotNull(helloService.greet("Ramos"));

        String ob1 = "Adios";
        String ob2 = "Adios";
        assertSame(ob1, ob2);
    }
}
