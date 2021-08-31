package com.example.myblog.comment;

import com.example.myblog.comment.form.CommentForm;
import com.example.myblog.post.Post;
import com.example.myblog.post.PostService;
import com.example.myblog.user.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
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

    @GetMapping("/comments/{commentId}")
    public String edit(@PathVariable Long commentId, Model model){
        Comment comment = commentService.findById(commentId);
        model.addAttribute("comment", comment);
        CommentForm commentForm = new CommentForm();
        commentForm.setContent(comment.getContent());

        model.addAttribute("commentForm", commentForm);
        return "comment/edit";
    }

    @PostMapping("/comments/{commentId}")
    public String edit(@PathVariable Long commentId, CommentForm commentForm){

        commentService.update(commentId, commentForm);


        return "redirect:/comments";
        //return "redirect:/posts/{postId}"; 이렇게 하고싶은데 계속 에러나면서 안됩니다...ㅜㅠ
    }


}
