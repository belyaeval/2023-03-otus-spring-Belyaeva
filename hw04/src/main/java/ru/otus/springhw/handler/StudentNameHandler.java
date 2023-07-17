package ru.otus.springhw.handler;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Student;

@Component
public class StudentNameHandler {
    public Student createStudentByName(String name) {
        return new Student(name);
    }
}
