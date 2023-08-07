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
import ru.otus.springhw.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Genre genre;

        try {
            genre = jdbc.queryForObject(
                    "select * from genre where id = :id", params, new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            genre = null;
        }

        return genre;
    }

    @Override
    public Genre insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());

        KeyHolder kh = new GeneratedKeyHolder();

        try {
            jdbc.update("insert into genre (name) values (:name)",
                    params, kh, new String[]{"id"}
            );

            genre.setId(kh.getKey().longValue());
        } catch (DuplicateKeyException e) {
           return null;
        }

        return genre;
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        Genre genre;

        try {
            genre = jdbc.queryForObject(
                    "select * from genre where name = :name", params, new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            genre = null;
        }

        return genre;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Genre(id, name);
        }
    }
}
