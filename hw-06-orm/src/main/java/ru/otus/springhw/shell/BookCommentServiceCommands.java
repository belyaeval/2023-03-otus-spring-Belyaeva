package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.service.BookCommentService;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommentServiceCommands {
    private final BookCommentService commentService;

    @ShellMethod(value = "Get all book comments", key = {"gabc", "get-all-b-c"})
    public String getAll(long id, String bName) {
        List<BookComment> bookComments = commentService.findAllByBookId(id);

        List<String> commentsString = new ArrayList<>();

        for (BookComment comm : bookComments) {
            commentsString.add(comm.getText());
        }

        return "Comments to book " + bName + " : \n" + commentsString;
    }
}
