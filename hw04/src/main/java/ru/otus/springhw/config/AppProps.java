package ru.otus.springhw.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {
    private String filePath;

    private int passScore;

    private Locale locale;

    public String getFilePath() {
        return filePath;
    }

    public int getPassScore() {
        return passScore;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setPassScore(int passScore) {
        this.passScore = passScore;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
