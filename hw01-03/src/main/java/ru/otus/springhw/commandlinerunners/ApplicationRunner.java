package ru.otus.springhw.commandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.springhw.service.TestService;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private final TestService testService;

    public ApplicationRunner(TestService testService) {
        this.testService = testService;
    }

    @Override
    public void run(String... args) throws Exception {
        testService.doTest();
    }
}
