package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllComment(Integer adId);

    CommentDTO createComment(Integer adId, String text);

    void deleteComment(Integer adId, Integer commentId);

    CommentDTO patchCommentId(Integer adId,Integer commentId, String text);
}
