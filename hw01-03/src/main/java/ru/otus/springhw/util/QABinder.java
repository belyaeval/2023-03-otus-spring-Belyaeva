package ru.otus.springhw.util;

import ru.otus.springhw.domain.TestItem;

public class QABinder {
    public TestItem bindQAItem(String[] qaArray) {
        int questionId;
        String questiobBody;
        String answers;
        int correctId;

        try{
            questionId = Integer.parseInt(qaArray[0]);
            questiobBody = qaArray[1];
            answers = qaArray[2];
            correctId = Integer.parseInt(qaArray[3]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("wrong test format");
        }

        return new TestItem(questionId, questiobBody, answers, correctId);
    }
}
