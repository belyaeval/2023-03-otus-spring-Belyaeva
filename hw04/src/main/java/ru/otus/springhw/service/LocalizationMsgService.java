package ru.otus.springhw.service;

public interface LocalizationMsgService {
    String getLocalizedMsg(String msgCode, String[] args);

    String getLocalizedMsg(String msgCode);
}
