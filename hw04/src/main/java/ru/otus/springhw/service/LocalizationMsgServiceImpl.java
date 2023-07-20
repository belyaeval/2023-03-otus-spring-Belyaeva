package ru.otus.springhw.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springhw.config.AppProps;

@Service
class LocalizationMsgServiceImpl implements LocalizationMsgService {
    private final MessageSource messageSource;

    private final AppProps appProps;

    public LocalizationMsgServiceImpl(MessageSource messageSource, AppProps appProps) {
        this.messageSource = messageSource;
        this.appProps = appProps;
    }

    @Override
    public String getLocalizedMsg(String msgCode, String[] args) {
        return messageSource.getMessage(msgCode, args, appProps.getLocale());
    }

    @Override
    public String getLocalizedMsg(String msgCode) {
        return messageSource.getMessage(msgCode, null, appProps.getLocale());
    }
}
