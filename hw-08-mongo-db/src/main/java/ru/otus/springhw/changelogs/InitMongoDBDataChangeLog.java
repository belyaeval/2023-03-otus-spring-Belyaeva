package ru.otus.springhw.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.domain.Genre;
import ru.otus.springhw.repository.AuthorRepository;
import ru.otus.springhw.repository.BookCommentRepository;
import ru.otus.springhw.repository.BookRepository;
import ru.otus.springhw.repository.GenreRepository;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    @ChangeSet(order = "000", id = "dropDB", author = "belyaeval", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "belyaeval", runAlways = true)
    public void initAuthors(AuthorRepository authorRepository) {
        authorRepository.save(new Author("1", "Пушкин"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "belyaeval", runAlways = true)
    public void initGenres(GenreRepository genreRepository) {
        genreRepository.save(new Genre("1", "повесть"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "belyaeval", runAlways = true)
    public void initBooks(BookRepository bookRepository) {
        bookRepository.save(new Book("1", "Метель", new Author("1", "Пушкин"), new Genre("1", "повесть")));
    }

    @ChangeSet(order = "004", id = "initBookComments", author = "belyaeval", runAlways = true)
    public void initBookComments(BookCommentRepository commentRepository) {
        commentRepository.save(new BookComment(
                "хорошая", new Book("1", "Метель", new Author("1", "Пушкин"), new Genre("1", "повесть")))
        );
    }
}
