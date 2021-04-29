package com.study.login.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ArticleDto {
    private long id;
    private String writer;
    private String content;
}
