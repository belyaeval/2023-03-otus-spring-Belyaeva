package ru.otus.springhw.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.springhw.dto.BookCommentsDto;
import ru.otus.springhw.dto.CommentDto;
import ru.otus.springhw.dto.BookDto;
import ru.otus.springhw.service.BookCommentService;
import ru.otus.springhw.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookCommentController {
    private final BookCommentService commentService;

    private final BookService bookService;

    @GetMapping("/comments")
    public String listComments(@RequestParam("bookId") long bookId, Model model) {
        BookCommentsDto comments = commentService.getAllBookComments(bookId);
        model.addAttribute("bookComments", comments);

        return "comments";
    }

    @GetMapping("/comments/add-comment")
    public String createComment(@RequestParam("bookId") long bookId, Model model) {
        BookDto bookDto = bookService.findById(bookId);
        model.addAttribute("comment", new CommentDto(null, bookDto.getId(), bookDto.getName()));

        return "add-comment";
    }

    @PostMapping("/comments/add-comment")
    public String addComment(@Valid @ModelAttribute("comment") CommentDto commentDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-comment";
        }

        commentService.addComment(commentDto.getBookId(), commentDto.getText());

        return "redirect:/comments?bookId=" + commentDto.getBookId();
    }
}
