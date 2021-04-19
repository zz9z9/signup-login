package com.study.login.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
@ToString
public class Member {

    @Id
    private String id;
    @Column
    private String password;
    @Column
    private String salt;

}
