package ru.otus.springhw.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.springhw.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Author author;

        try {
            author = jdbc.queryForObject(
                    "select * from author where id = :id", params, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            author = null;
        }

        return author;
    }

    @Override
    public Author insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());

        KeyHolder kh = new GeneratedKeyHolder();

        try {
            jdbc.update("insert into author (name) values (:name)",
                    params, kh, new String[]{"id"}
            );

            author.setId(kh.getKey().longValue());
        } catch (DuplicateKeyException e) {
            return null;
        }

        return author;
    }

    @Override
    public Author getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        Author author;

        try {
            author = jdbc.queryForObject(
                    "select * from author where name = :name", params, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            author = null;
        }

        return author;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Author(id, name);
        }
    }
}
