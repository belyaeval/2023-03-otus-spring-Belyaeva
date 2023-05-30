package ru.otus.springhw.domain;

public class Student {
    private final String name;

    private int correctAnswersCount = 0;


    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void incCorrectAnswersCount() {
        correctAnswersCount++;
    }
}
