package ru.otus.springhw.handler;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.TestItem;

@Component
public class QuestionAnswersHandler {
    public TestItem bindQAItem(String[] qaArray) {
        int questionId;
        String questionBody;
        String answers;
        int correctId;

        try {
            questionId = Integer.parseInt(qaArray[0]);
            questionBody = qaArray[1];
            answers = qaArray[2];
            correctId = Integer.parseInt(qaArray[3]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("wrong test format");
        }

        return new TestItem(questionId, questionBody, answers, correctId);
    }
}
