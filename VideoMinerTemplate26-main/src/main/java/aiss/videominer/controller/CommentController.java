package aiss.videominer.controller;

import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    // GET /videominer/comments - Listar todos los comentarios
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // GET /videominer/comments/{id} - Obtener un comentario por ID
    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(null);
    }

    // POST /videominer/comments - Crear un nuevo comentario
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    // PUT /videominer/comments/{id} - Actualizar un comentario existente
    @PutMapping("/comments/{id}")
    public Comment updateComment(@PathVariable String id, @RequestBody Comment comment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment updated = existingComment.get();
            updated.setText(comment.getText());
            updated.setCreatedOn(comment.getCreatedOn());
            return commentRepository.save(updated);
        }
        return null;
    }

    // DELETE /videominer/comments/{id} - Eliminar un comentario
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id) {
        commentRepository.deleteById(id);
    }
}