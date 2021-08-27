package com.example.myblog.comment;

import com.example.myblog.user.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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



}
