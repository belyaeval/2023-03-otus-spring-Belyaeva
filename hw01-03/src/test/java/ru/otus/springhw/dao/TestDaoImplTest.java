package ru.otus.springhw.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springhw.domain.TestItem;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс TestDaoImplTest")
@SpringBootTest
public class TestDaoImplTest {
    @Autowired
    private TestDao testDao;

    @DisplayName("Проверяет, что в файле 5 вопросов")
    @Test
    public void shouldReturnTestQuestionsNumber() {
        int qNumber = 5;
        List<TestItem> testItemList = testDao.getAll();

        assertThat(testItemList).hasSize(qNumber);
    }
}
