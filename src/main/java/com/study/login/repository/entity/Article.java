package com.study.login.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
@ToString
@Entity
@Table(name = "ARTICLE")
public class Article {
    @Id @GeneratedValue
    private long id;
    @Column
    private String writer;
    @Column
    private String content;
}
