package aiss.videominer.controller;

import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import aiss.videominer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videominer")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable String id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", id));
    }
}