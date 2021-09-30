package com.example.myblog.post;

import com.example.myblog.comment.Comment;
import com.example.myblog.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) //하나의 포스트는 한명의 유저에게
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post") //한개의 포스트가 여러개의 커맨트를 가짐
    private List<Comment> comments;
}
