package com.example.myblog.comment;

import com.example.myblog.comment.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment findById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
    }



    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    @Transactional
    public void update(Long commentId, CommentForm commentForm){
        Comment comment = findById(commentId);

        comment.setContent(commentForm.getContent());
    }
}
