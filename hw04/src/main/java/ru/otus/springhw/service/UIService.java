package ru.otus.springhw.service;

import ru.otus.springhw.domain.Student;
import ru.otus.springhw.domain.TestItem;

public interface UIService {
    void askQuestion(TestItem testItem);

    int getCorrectNumber();

    void printQuestionResult(boolean isCorrect, TestItem ti);

    void printTestResult(Student student);


}
