package com.study.login.service.impl;

import com.study.login.domain.dto.ArticleDto;
import com.study.login.repository.entity.Article;
import com.study.login.repository.ArticleRepository;
import com.study.login.service.spec.ArticleService;
import com.study.login.service.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto findById(long id) {
        Article findArticle = articleRepository.findById(id).orElse(new Article());
        return ObjectMapperUtils.map(findArticle, ArticleDto.class);
    }

    @Override
    public List<ArticleDto> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Article> articles = articleRepository.findAll();

        System.out.println("articles = " + articles);

        // List<ArticleDto> articleDtoList = ObjectMapperUtils.mapAll(articles, ArticleDto.class);
        List<ArticleDto> articleDtoList = articles
                .stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());

        System.out.println("articleDtoList ==== " + articleDtoList);

        return articleDtoList;
    }

    @Override
    public long save(ArticleDto articleDto) {
        ModelMapper modelMapper = new ModelMapper();
        Article article = modelMapper.map(articleDto, Article.class);

        articleRepository.save(article);

        return article.getId();
    }

    @Override
    public void remove(long id) {
        articleRepository.deleteById(id);
    }
}
