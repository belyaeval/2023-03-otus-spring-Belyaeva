package ru.otus.springhw.service;

import org.springframework.stereotype.Service;
import ru.otus.springhw.config.AppProps;
import ru.otus.springhw.domain.Student;
import ru.otus.springhw.domain.TestItem;

@Service
public class UIServiceImpl implements UIService {
    private final LocalizationMsgService msgServ;

    private final IOService ioService;

    private final AppProps appProps;

    public UIServiceImpl(LocalizationMsgService msgServ, IOService ioService, AppProps appProps) {
        this.msgServ = msgServ;
        this.ioService = ioService;
        this.appProps = appProps;
    }

    @Override
    public void askQuestion(TestItem ti) {
        ioService.outputString(("\n" + msgServ.getLocalizedMsg("question.id")) + ti.getQuestionId());
        ioService.outputString(ti.getQuestionBody());
        ioService.outputString(ti.getAnswers());
        ioService.outputString(msgServ.getLocalizedMsg("student.answer.id"));
    }

    @Override
    public int getCorrectNumber() {
        int answerId;

        try {
            answerId = ioService.readInt();
        } catch (NumberFormatException e) {
            throw new NumberFormatException(msgServ.getLocalizedMsg("wrong.answer.format"));
        }

        return answerId;
    }

    @Override
    public void printQuestionResult(boolean isCorrect, TestItem ti) {
        if (isCorrect) {
            ioService.outputString(msgServ.getLocalizedMsg("correct.answer"));
        } else {
            ioService.outputString(msgServ.getLocalizedMsg("incorrect.answer") + ti.getCorrectId());
        }
    }

    @Override
    public void printTestResult(Student student) {
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
