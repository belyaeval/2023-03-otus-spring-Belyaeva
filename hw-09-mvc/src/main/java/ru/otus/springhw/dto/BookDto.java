package ru.otus.springhw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDto {
    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    private String name;

    @NotBlank(message = "{author-field-should-not-be-blank}")
    private String author;

    @NotBlank(message = "{genre-field-should-not-be-blank}")
    private String genre;

    public BookDto(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
