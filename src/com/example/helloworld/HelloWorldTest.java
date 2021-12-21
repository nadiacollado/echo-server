package com.example.helloworld;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {
    @Test
    void sayHello() {
        HelloWorld sample = new HelloWorld();
        assertEquals("Hello, Nadia", sample.sayHello("Nadia"));
    }
}