package ru.otus.springhw.dao;

import ru.otus.springhw.domain.TestItem;

import java.util.List;

public interface TestDao {
    List<TestItem> getAll();
}
