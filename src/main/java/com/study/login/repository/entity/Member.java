package com.study.login.repository.entity;

import com.study.login.repository.converter.StringListConverter;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MEMBER")
@ToString
public class Member implements UserDetails {

    @Id
    @Column(name = "member_id")
    private String id;
    @Column
    private String password;
    @Column
    private String salt;
//    @OneToMany(mappedBy = "writer")
//    private List<Article> articles;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> roles;

    @Builder
    public Member(String id) {
        this.id = id;
    }

    public Member() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
