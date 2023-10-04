package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.dto.BookDto;
import ru.otus.springhw.dto.mapper.BookDtoMapper;
import ru.otus.springhw.exception.NotFoundException;
import ru.otus.springhw.repository.BookRepository;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookDtoMapper bookDtoMapper;

    @Override
    @Transactional
    public BookDto add(BookDto bookDto) {
        Book existingBook = bookRepository.findByName(bookDto.getName()).orElse(null);
        Book bookToSave = bookDtoMapper.fromDto(bookDto);

        if (existingBook == null) {
            validateBook(bookToSave);

            return bookDtoMapper.toDto(bookRepository.save(bookToSave));
        }

        String existingBookAName = existingBook.getAuthor().getName();
        String newBookAName = bookToSave.getAuthor().getName();

        if (existingBookAName.equals(newBookAName)) {
            throw new RuntimeException();
        }

        validateBook(bookToSave);

        return bookDtoMapper.toDto(bookRepository.save(bookToSave));
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));

        return bookDtoMapper.toDto(book);
    }

    @Override
    @Transactional
    public BookDto updateById(long id, BookDto bookDto) {
        try {
            findById(id);

            Book book = bookDtoMapper.fromDto(bookDto);
            validateBook(book);
            book.setId(id);

            return bookDtoMapper.toDto(bookRepository.save(book));
        } catch (NotFoundException e) {
            throw new NotFoundException("Book not found");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
            bookRepository.delete(book);
        } catch (NotFoundException e) {
            throw new NotFoundException("Book not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(bookDtoMapper::toDto).collect(Collectors.toList());
    }

    private Author validateAuthor(Author author) {
        return authorService.findByName(author.getName()).orElseGet(() -> authorService.save(author));
    }

    private Genre validateGenre(Genre genre) {
        return genreService.findByName(genre.getName()).orElseGet(() -> genreService.save(genre));
    }

    private void validateBook(Book book) {
        Author author = validateAuthor(book.getAuthor());
        Genre genre = validateGenre(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);
    }
}
