package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.service.BookCommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommentServiceCommands {
    private final BookCommentService commentService;

    @ShellMethod(value = "Add comment", key = {"ac", "add-comm"})
    public String addCommentToBook(long bId, String commText) {
        commentService.addComment(bId, commText);

        return "You add comment: " + commText;
    }

    @ShellMethod(value = "Get all books comments", key = {"gabc", "get-all-b-c"})
    public String getAllComments(long bId) {
        List<String> commsString = commentService.getAllBookComments(bId);

        return "Comments to book with id: " + bId + " : \n" + commsString;
    }
}
