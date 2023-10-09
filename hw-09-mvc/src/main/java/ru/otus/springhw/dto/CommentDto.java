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
public class CommentDto {
    private long id;

    @NotBlank(message = "{comment-field-should-not-be-blank}")
    private String text;

    private long bookId;

    private String bookName;

    public CommentDto(String text, long bookId, String bookName) {
        this.text = text;
        this.bookId = bookId;
        this.bookName = bookName;
    }
}
