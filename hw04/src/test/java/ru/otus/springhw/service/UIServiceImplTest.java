package ru.otus.springhw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springhw.config.AppProps;
import ru.otus.springhw.domain.Student;
import ru.otus.springhw.domain.TestItem;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("класс UIServiceImplTest")
public class UIServiceImplTest {
    @MockBean
    private IOService ioService;

    @MockBean
    private AppProps appProps;

    @MockBean
    private LocalizationMsgServiceImpl localizedMsgServ;

    @Autowired
    private UIServiceImpl uiService;

    @DisplayName("проверяет, что сервис задает вопрос")
    @Test
    public void shouldGreetAskQuestionReturnRightAnswer() {
        TestItem testItem = new TestItem(1, "How many planets are in our solar system", "1.5 2.8 3.10", 2);

        given(localizedMsgServ.getLocalizedMsg("question.id")).willReturn("Question ");
        given(localizedMsgServ.getLocalizedMsg("student.answer.id")).willReturn("Pick an answer id:");
        given(localizedMsgServ.getLocalizedMsg("correct.answer")).willReturn("You are right!");

        uiService.askQuestion(testItem);

        verify(ioService).outputString(contains("Question"));
        verify(ioService).outputString(testItem.getQuestionBody());
        verify(ioService).outputString(testItem.getAnswers());
        verify(ioService).outputString(contains("Pick an answer id"));
    }

    @DisplayName("проверяет, что сервис сообщает о прохождении теста, выводит имя студента и количество баллов")
    @Test
    public void shouldReturnScoresForPassingTestWithStudentName() {
        int expectedPassingScores = 1;
        Student student = new Student("John Doe");
        student.incCorrectAnswersCount();
        String expectedName = student.getName();

        given(localizedMsgServ.getLocalizedMsg("test.score", new String[]{expectedName})).willReturn(expectedName + ", your result:");
        given(localizedMsgServ.getLocalizedMsg("test.pass")).willReturn("You passed the test");
        given(appProps.getPassScore()).willReturn(expectedPassingScores);

        uiService.printTestResult(student);

        verify(ioService).outputString(matches((expectedName) + ".+" + expectedPassingScores));
        verify(ioService).outputString(contains("You passed the test"));

    }
}
