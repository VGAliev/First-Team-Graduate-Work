package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import org.springframework.http.HttpStatus;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.service.CommentService;

import java.util.List;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    @GetMapping("{id}/comments")
    public ResponseEntity<CommentsDTO> getCommentsByAdsId(@PathVariable Integer id,Authentication authentication) {
        if (authentication.isAuthenticated()) {
            CommentsDTO commentsDTO = new CommentsDTO();
            List<CommentDTO> allComment = commentService.getAllComment(id);
            commentsDTO.setCount(allComment.size() - 1);
            commentsDTO.setResults(allComment);

            return ResponseEntity.ok(commentsDTO);
        }else {
            return ResponseEntity.ok().build();
        }

    }

    @PostMapping(value = "{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer adsId, @RequestBody(required = false)CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            CommentEntity comment = commentMapper.toComment(createOrUpdateComment);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(adsId,comment));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("{adsId}/comments/{commentId}")
    public ResponseEntity deleteCommentsByAdsId(@PathVariable Integer adsId,@PathVariable Integer commentId) {
    commentService.deleteComment(adsId,commentId);
    return ResponseEntity.ok().build();
    }

    @PutMapping("{adsId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adsId,@PathVariable Integer commentId, String text) {
        return ResponseEntity.ok(commentService.patchCommentId(adsId,commentId,text)) ;
    }

}