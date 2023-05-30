package ru.otus.springhw.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.springhw.config.AppProps;

@Component
class LocalizationMessageService {
    private final MessageSource messageSource;

    private final AppProps appProps;

    public LocalizationMessageService(MessageSource messageSource, AppProps appProps) {
        this.messageSource = messageSource;
        this.appProps = appProps;
    }

    public String getLocalizedMsg(String msgCode, String[] args) {
        return messageSource.getMessage(msgCode, args, appProps.getLocale());
    }

    public String getLocalizedMsg(String msgCode) {
        return messageSource.getMessage(msgCode, null, appProps.getLocale());
    }
}
