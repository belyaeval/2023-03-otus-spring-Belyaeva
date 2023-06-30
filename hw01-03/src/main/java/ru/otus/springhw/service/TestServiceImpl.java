package ru.otus.springhw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springhw.config.AppProps;
import ru.otus.springhw.dao.TestDao;
import ru.otus.springhw.domain.Student;
import ru.otus.springhw.domain.TestItem;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestDao testDao;

    private final IOService ioService;

    private final AppProps appProps;

    private final LocalizationMsgService msgServ;

    @Autowired
    public TestServiceImpl(TestDao testDao, IOService ioService, AppProps appProps, LocalizationMsgService msgServ) {
        this.testDao = testDao;
        this.ioService = ioService;
        this.appProps = appProps;
        this.msgServ = msgServ;
    }

    @Override
    public List<TestItem> getAllTestItems() {
        return testDao.getAll();
    }

    @Override
    public void doTest() {
        List<TestItem> testItems = getAllTestItems();
        Student student = greet();

        for (TestItem ti : testItems) {
            ioService.outputString(("\n" + msgServ.getLocalizedMsg("question.id")) + ti.getQuestionId());
            ioService.outputString(ti.getQuestionBody());
            ioService.outputString(ti.getAnswers());
            ioService.outputString(msgServ.getLocalizedMsg("student.answer.id"));
            int answerId = getCorrectNumber();
            boolean isCorrectAnswer = isCorrectAnswer(answerId, ti, student);

            printQuestionResult(isCorrectAnswer, ti);
        }

        printTestResult(student);
    }


    private Student greet() {
        String helloMgsLocalized = msgServ.getLocalizedMsg("hello.user");
        String studentName = ioService.readStringWithPrompt(helloMgsLocalized);

        return new Student(studentName);
    }

    private int getCorrectNumber() {
        int answerId;

        try {
            answerId = ioService.readInt();
        } catch (NumberFormatException e) {
            throw new NumberFormatException(msgServ.getLocalizedMsg("wrong.answer.format"));
        }

        return answerId;
    }

    private boolean isCorrectAnswer(int answerId, TestItem ti, Student student) {
        if (answerId == ti.getCorrectId()) {
            student.incCorrectAnswersCount();

            return true;
        }

        return false;
    }

    private void printQuestionResult(boolean isCorrect, TestItem ti) {
        if (isCorrect) {
            ioService.outputString(msgServ.getLocalizedMsg("correct.answer"));
        } else {
            ioService.outputString(msgServ.getLocalizedMsg("incorrect.answer") + ti.getCorrectId());
        }
    }

    private void printTestResult(Student student) {
        int studentResult = student.getCorrectAnswersCount();
        String name = student.getName();

        ioService.outputString(("\n" + msgServ.getLocalizedMsg("test.score", new String[]{name})) + studentResult);

        if (studentResult >= appProps.getPassScore()) {
            ioService.outputString(msgServ.getLocalizedMsg("test.pass"));
        } else {
            ioService.outputString(msgServ.getLocalizedMsg("test.failed"));
        }
    }
}
