package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private AdRepository adRepository;
    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> getAllComment(Integer adId) {
        List<CommentEntity> comment = adRepository.findById(adId).get().getComment();
        List<CommentDTO> collect = comment.stream().map(a -> commentMapper.toDto(a))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDTO createComment(Integer adId, String text) {
        CommentEntity commentEntity = new CommentEntity(text);
        adRepository.findById(adId).get().getComment().add(commentEntity);
        return commentMapper.toDto(commentEntity);
    }

     @Override
    public void deleteComment(Integer adId, Integer commentId) {
        adRepository.findById(adId).get().getComment().remove(commentId);
    }

    @Override
    public CommentDTO patchCommentId(Integer adId,Integer commentId, String text) {
        return adRepository.findById(adId).get().getComment().get(commentId).setText(text);

    }
}
