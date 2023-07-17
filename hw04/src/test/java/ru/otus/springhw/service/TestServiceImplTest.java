package ru.otus.springhw.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springhw.dao.TestDao;
import ru.otus.springhw.domain.Student;
import ru.otus.springhw.domain.TestItem;
import ru.otus.springhw.handler.StudentNameHandler;

import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Класс TestServiceImpl")
public class TestServiceImplTest {
    @MockBean
    private TestDao testDao;

    @MockBean
    private StudentNameHandler studentNameHandler;

    @MockBean
    private UIServiceImpl uiService;

    @Autowired
    private TestServiceImpl testService;

    @DisplayName("Проверяет, что введен правильный ответ")
    @Test
    public void shouldReturnRightAnswer() {
        TestItem testItem = new TestItem(1, "How many planets are in our solar system", "1.5 2.8 3.10", 2);
        List<TestItem> testItemList = List.of(testItem);
        int expectedAnswer = 2;
        String name = "John Doe";
        Student student = new Student(name);

        given(testDao.getAll()).willReturn(testItemList);
        given(studentNameHandler.createStudentByName(name)).willReturn(student);
        given(uiService.getCorrectNumber()).willReturn(expectedAnswer);

        testService.runTest(name);
        Assertions.assertThat(testItem.getCorrectId()).isEqualTo(expectedAnswer);
    }
}
