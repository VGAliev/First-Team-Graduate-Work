package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private AdRepository adRepository;
    private CommentMapper commentMapper;
    private UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapper, UserService userService) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    @Override
    public List<CommentDTO> getAllComment(Integer adId) {
        List<CommentEntity> comment = adRepository.findById(adId).get().getComments();
        List<CommentDTO> collect = comment.stream().map(a -> commentMapper.toDto(a))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDTO createComment(Integer adId, CommentEntity comment) {
        UserDto user = userService.getUser();

        LocalDateTime localDateTime = LocalDateTime.now();

        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();
        comment.setCreatedAt(date);
        comment.setAuthor(user.getId());
        comment.setAuthorFirstName(user.getFirstName());
        comment.setAd(adRepository.findById(adId).get());
        comment.setAuthorImage(user.getImage());

        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        adRepository.findById(adId).get().getComments().remove(commentId);
    }

    @Override
    public CommentDTO patchCommentId(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        CommentEntity commentEntity = adRepository.findById(adId).get().getComments().get(commentId);
        commentEntity.setText(createOrUpdateComment.getText());
        return commentMapper.toDto(commentRepository.save(commentEntity));
    }
}
