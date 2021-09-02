package com.example.myblog.user;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

    private com.example.myblog.user.User user;

    public UserAccount(com.example.myblog.user.User user){
        super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getType())));
        this.user = user;
    }
}
