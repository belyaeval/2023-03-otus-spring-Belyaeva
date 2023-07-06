package ru.otus.springhw.service;

import ru.otus.springhw.domain.TestItem;

import java.util.List;

public interface TestService {
    List<TestItem> getAllTestItems();

    void runTest();
}
