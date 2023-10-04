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
import ru.otus.springhw.dto.BookDto;
import ru.otus.springhw.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public String listBooks(Model model) {
        List<BookDto> bookDtos = bookService.findAll();

        model.addAttribute("books", bookDtos);

        return "list";
    }

    @GetMapping("/edit")
    public String findBookToEdit(@RequestParam("id") long id, Model model) {
        BookDto bookDto = bookService.findById(id);
        model.addAttribute("book", bookDto);

        return "edit-book";
    }

    @GetMapping("/delete")
    public String findBookToDelete(@RequestParam("id") long id, Model model) {
        BookDto bookDto = bookService.findById(id);
        model.addAttribute("book", bookDto);

        return "delete-book";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") @Valid BookDto bookDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-book";
        }

        bookService.updateById(bookDto.getId(), bookDto);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBook(BookDto bookDto) {
        bookService.deleteById(bookDto.getId());

        return "redirect:/";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookDto());

        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@Valid @ModelAttribute("book") BookDto bookDto,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", bookDto);

            return "add-book";
        }

        bookService.add(bookDto);

        return "redirect:/";
    }
}
