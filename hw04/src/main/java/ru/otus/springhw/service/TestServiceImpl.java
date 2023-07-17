package ru.otus.springhw.service;

import org.springframework.stereotype.Service;
import ru.otus.springhw.handler.StudentNameHandler;
import ru.otus.springhw.dao.TestDao;
import ru.otus.springhw.domain.Student;
import ru.otus.springhw.domain.TestItem;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestDao testDao;

    private final StudentNameHandler studentNameHandler;

    private final UIService uiService;

    public TestServiceImpl(TestDao testDao, StudentNameHandler studentNameHandler, UIServiceImpl uiService) {
        this.testDao = testDao;
        this.studentNameHandler = studentNameHandler;
        this.uiService = uiService;
    }

    @Override
    public List<TestItem> getAllTestItems() {
        return testDao.getAll();
    }

    @Override
    public void runTest(String name) {
        List<TestItem> testItems = getAllTestItems();
        Student student = studentNameHandler.createStudentByName(name);

        for (TestItem ti : testItems) {
            uiService.askQuestion(ti);

            int answerId = uiService.getCorrectNumber();
            boolean isCorrectAnswer = isCorrectAnswer(answerId, ti, student);

            uiService.printQuestionResult(isCorrectAnswer, ti);
       }

        uiService.printTestResult(student);
    }

    private boolean isCorrectAnswer(int answerId, TestItem ti, Student student) {
        if (answerId == ti.getCorrectId()) {
            student.incCorrectAnswersCount();

            return true;
        }

        return false;
    }
}
