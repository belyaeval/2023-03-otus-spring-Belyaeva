package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.service.BookCommentService;

import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
@RequiredArgsConstructor
public class BookCommentServiceCommands {
    private final BookCommentService commentService;

    @ShellMethod(value = "Add comment", key = {"ac", "add-comm"})
    public String addCommentToBook(String bId, String commText) {
        try {
            commentService.addComment(bId, commText);

            return "You add comment: " + commText;
        } catch (NoSuchElementException e) {
            return String.format("Book  with id %s doesn't exist", bId);
        }
    }

    @ShellMethod(value = "Get all books comments", key = {"gabc", "get-all-b-c"})
    public String getAllComments(String bId) {
        List<String> commsString = commentService.getAllBookComments(bId);

        return "Comments to book with id: " + bId + " : \n" + commsString;
    }
}
