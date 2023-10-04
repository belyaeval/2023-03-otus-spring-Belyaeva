package ru.otus.springhw.service;

import ru.otus.springhw.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto add(BookDto bookDto);

    BookDto findById(Long id);

    BookDto updateById(long id, BookDto bookDto);

    void deleteById(Long id);

    List<BookDto> findAll();
}
