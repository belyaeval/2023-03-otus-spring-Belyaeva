package ru.otus.springhw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springhw.config.AppProps;
import ru.otus.springhw.dao.TestDao;
import ru.otus.springhw.domain.TestItem;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс TestServiceImpl")
@ExtendWith(MockitoExtension.class)
public class TestServiceImplTest {
    @Mock
    private TestDao testDao;

    @Mock
    private IOService ioService;

    @Mock
    private AppProps appProps;

    @Mock
    private LocalizationMsgService localizedMsgServ;

    @InjectMocks
    private TestServiceImpl testService;

    @DisplayName("проверяет, что сервис здоровается, задает вопрос и проверяет, введен ли правильный ответ")
    @Test
    public void shouldGreetAskQuestionReturnRightAnswer() {
        TestItem testItem = new TestItem(1, "How many planets are in our solar system", "1.5 2.8 3.10", 2);
        List<TestItem> testItemList = List.of(testItem);
        int expectedAnswer = 2;

        given(localizedMsgServ.getLocalizedMsg("hello.user")).willReturn("Hello! Please, enter your name and surname:");
        given(localizedMsgServ.getLocalizedMsg("question.id")).willReturn("Question ");
        given(localizedMsgServ.getLocalizedMsg("student.answer.id")).willReturn("Pick an answer id:");
        given(localizedMsgServ.getLocalizedMsg("correct.answer")).willReturn("You are right!");
        given(testDao.getAll()).willReturn(testItemList);
        given(ioService.readInt()).willReturn(expectedAnswer);

        testService.runTest();

        verify(ioService, times(1)).readStringWithPrompt("Hello! Please, enter your name and surname:");
        verify(ioService).outputString(contains("Question"));
        verify(ioService).outputString(testItem.getQuestionBody());
        verify(ioService).outputString(testItem.getAnswers());
        verify(ioService).outputString(contains("Pick an answer id"));
        verify(ioService).outputString("You are right!");
    }

    @DisplayName("проверяет, что сервис сообщает о прохождении теста, выводит имя студента и количество баллов")
    @Test
    public void shouldReturnScoresForPassingTestWithStudentName() {
        TestItem testItem = new TestItem(1, "How many planets are in our solar system", "1.5 2.8 3.10", 2);
        List<TestItem> testItemList = List.of(testItem);
        int expectedAnswer = 2;
        int expectedPassingScores = 1;
        String expectedName = "John Doe";

        given(localizedMsgServ.getLocalizedMsg("hello.user")).willReturn("Hello! Please, enter your name and surname:");
        given(ioService.readStringWithPrompt(anyString())).willReturn(expectedName);
        given(localizedMsgServ.getLocalizedMsg("question.id")).willReturn("Question ");
        given(localizedMsgServ.getLocalizedMsg("student.answer.id")).willReturn("Pick an answer id:");
        given(localizedMsgServ.getLocalizedMsg("correct.answer")).willReturn("You are right!");
        given(localizedMsgServ.getLocalizedMsg("test.score", new String[]{expectedName})).willReturn(expectedName + ", your result:");
        given(localizedMsgServ.getLocalizedMsg("test.pass")).willReturn("You passed the test");
        given(testDao.getAll()).willReturn(testItemList);
        given(ioService.readInt()).willReturn(expectedAnswer);
        given(appProps.getPassScore()).willReturn(expectedPassingScores);

        testService.runTest();

        verify(ioService).outputString(matches((expectedName) + ".+" + expectedPassingScores));
        verify(ioService).outputString(contains("You passed the test"));

    }



}
