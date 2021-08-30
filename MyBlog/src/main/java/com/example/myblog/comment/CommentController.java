package com.example.myblog.comment;

import com.example.myblog.comment.form.CommentForm;
import com.example.myblog.user.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/comments")
    public String index(Model model){
        model.addAttribute("comments", commentService.findAll());

        return "comment/index";
    }

//    @PostMapping("/comments")
//    public String writeComment(CommentForm commentForm){
//       // model.addAttribute("comments", commentService.findAll());
//
//        return "comment/index";
//    }



}
