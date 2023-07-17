package ru.otus.springhw.dao;

import org.springframework.stereotype.Component;
import ru.otus.springhw.config.AppProps;
import ru.otus.springhw.domain.TestItem;
import ru.otus.springhw.handler.QuestionAnswersHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDaoImpl implements TestDao {
    private final AppProps appProps;

    private final QuestionAnswersHandler questionAnswersHandler;

    public TestDaoImpl(AppProps appProps, QuestionAnswersHandler questionAnswersHandler) {
        this.appProps = appProps;
        this.questionAnswersHandler = questionAnswersHandler;
    }

    @Override
    public List<TestItem> getAll() {
        String line;
        String lineSeparator = ",";
        List<TestItem> testItems = new ArrayList<>();
        String filePath = appProps.getFilePath();

        InputStream is = getFileFromResourceAsStream(filePath);

        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(lineSeparator);
                TestItem item = questionAnswersHandler.bindQAItem(s);

                testItems.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read file");
        }

        return testItems;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
