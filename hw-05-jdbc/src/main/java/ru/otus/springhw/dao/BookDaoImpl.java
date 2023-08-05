package ru.otus.springhw.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());

        KeyHolder kh = new GeneratedKeyHolder();

        jdbc.update("insert into book(name, author_id, genre_id) values(:name, :author_id, :genre_id)",
                params, kh, new String[]{"id"});

        book.setId(kh.getKey().longValue());

        return book;
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "select b.id as id, b.name as name, " +
                "a.id as author_id, a.name as author_name, " +
                "g.id as genre_id, g.name as genre_name from book as b " +
                "inner join author as a " +
                "on b.author_id = a.id " +
                "inner join genre as g " +
                "on b.genre_id = g.id " +
                "where b.id = :id";
        Book book;

        try {
            book = jdbc.queryForObject(sql, params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            book = null;
        }

        return book;
    }

    @Override
    public Book update(Book book, long id) {
        Map<String, Object> params = Map.of("id", book.getId(), "name", book.getName(),
                "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId());

        int row = jdbc.update("update book set name = :name, author_id = :author_id, genre_id = :genre_id " +
                        "where id = :id",
                params);

        if (row == 0) {
            return null;
        }

        return book;
    }

    @Override
    public Book deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Book book = getById(id);

        jdbc.update("delete from book where id = :id", params);

        return book;
    }

    @Override
    public List<Book> getAll() {
        String sql = "select b.id as id, b.name as name, " +
                "a.id as author_id, a.name as author_name, " +
                "g.id as genre_id, g.name as genre_name from book as b " +
                "inner join author as a " +
                "on b.author_id = a.id " +
                "inner join genre as g " +
                "on b.genre_id = g.id ";

        return jdbc.query(sql, new BookMapper());
    }

    @Override
    public Book getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        String sql = "select b.id as id, b.name as name, " +
                "a.id as author_id, a.name as author_name, " +
                "g.id as genre_id, g.name as genre_name from book as b " +
                "inner join author as a " +
                "on b.author_id = a.id " +
                "inner join genre as g " +
                "on b.genre_id = g.id " +
                "where b.name = :name";
        Book book;

        try {
            book = jdbc.queryForObject(sql, params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            book = null;
        }

        return book;
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long bookId = rs.getLong("id");
            String bookName = rs.getString("name");
            long authorId = rs.getLong("author_id");
            String authorName = rs.getString("author_name");
            long genreId = rs.getLong("genre_id");
            String genreName = rs.getString("genre_name");

            return new Book(bookId, bookName, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
    }
}
