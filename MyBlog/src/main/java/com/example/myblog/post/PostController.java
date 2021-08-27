package com.example.myblog.post;

import com.example.myblog.comment.Comment;
import com.example.myblog.comment.CommentService;
import com.example.myblog.comment.form.CommentForm;
import com.example.myblog.post.form.PostForm;
import com.example.myblog.user.User;
import com.example.myblog.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());

        return "post/index";
    }

    @GetMapping("/posts/{postId}")
    public String show(@PathVariable Long postId, @Valid CommentForm commentForm, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.findAll());

        return "post/show";
    }

    @PostMapping("/posts/{postId}")
    public String newComment(@PathVariable Long postId, CommentForm commentForm){
        Post post = postService.findById(postId);

        User user = userService.findById(1L);

        Comment comment = Comment.builder()
                .content(commentForm.getContent())
                .user(user)
                .post(post)
                .build();
        commentService.save(comment);

        return "redirect:/post/show";
    }

    @GetMapping("/new-post")
    public String newPost(Model model) {
        model.addAttribute("postForm", new PostForm());

        return "post/new";
    }

    @PostMapping("/new-post")
    public String create(@Valid PostForm postForm, Errors errors) {
        if (errors.hasErrors()) {
            return "post/new";
        }

        User user = userService.findById(1L);

        Post post = Post.builder()
                .title(postForm.getTitle())
                .description(postForm.getDescription())
                .user(user)
                .build();
        postService.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/edit-post/{postId}")
    public String edit(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);

        PostForm postForm = new PostForm();
        postForm.setTitle(post.getTitle());
        postForm.setDescription(post.getDescription());
        model.addAttribute("postForm", postForm);

        return "post/edit";
    }

    @PostMapping("/edit-post/{postId}")
    public String edit(@PathVariable Long postId, PostForm postForm) {
        postService.update(postId, postForm);
        return "redirect:/posts";
    }
}
