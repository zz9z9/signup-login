package com.study.login.service.spec;

import com.study.login.domain.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto findById(long id);

    List<ArticleDto> findAll();

    long save(ArticleDto articleDto);

    void remove(long id);
}
