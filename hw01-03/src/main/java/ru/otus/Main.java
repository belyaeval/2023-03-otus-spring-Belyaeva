package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.springhw.service.TestService;
import ru.otus.springhw.service.TestServiceImpl;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        TestService runner = context.getBean(TestServiceImpl.class);

        runner.doTest();
    }
}