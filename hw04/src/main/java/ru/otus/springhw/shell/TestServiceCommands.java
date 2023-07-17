package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.springhw.service.TestService;

@ShellComponent
@RequiredArgsConstructor
public class TestServiceCommands {
    private final TestService testService;

    private String userName;

    @ShellMethod(value = "Hello command", key = {"h", "hello"})
    public String hello(String userName) {
        this.userName = userName;
        return String.format("Здравствуйте, %s!", userName);
    }

    @ShellMethod(value = "Run test command", key = {"r", "run"})
    @ShellMethodAvailability(value = "isRunTestAvailable")
    public void run() {
        testService.runTest(userName);
    }

    private Availability isRunTestAvailable() {
        return userName == null ? Availability.unavailable("Сначала представьтесь") : Availability.available();
    }
}
