package ru.otus.springhw.domain;


public class TestItem {
    private final int questionId;

    private final String questionBody;

    private final String answers;

    private final int correctId;

    public TestItem(int questionId, String questionBody, String answers, int correctId) {
        this.questionId = questionId;
        this.questionBody = questionBody;
        this.answers = answers;
        this.correctId = correctId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public String getAnswers() {
        return answers;
    }

    public int getCorrectId() {
        return correctId;
    }
}
