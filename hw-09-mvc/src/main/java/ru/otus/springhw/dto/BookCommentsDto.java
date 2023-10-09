package ru.otus.springhw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BookCommentsDto {
    private long id;

    private String name;

    private List<CommentDto> comments;
}
