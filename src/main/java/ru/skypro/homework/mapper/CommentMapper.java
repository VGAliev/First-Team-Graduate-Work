package ru.skypro.homework.mapper;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class CommentMapper {
    private UserRepository userRepository;

    public Comment commentDtoToComment(CommentDTO dto) {
        User author = userRepository.findById(dto.getAuthor()).orElseThrow();
        Comment comment = new Comment();
        comment.setPk(dto.getPk());
        comment.setAuthor(author);
        comment.setText(dto.getText());
        comment.setCreatedAt(dto.getCreatedAt());

        return comment;
    }

    public CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();

        dto.setPk(comment.getPk());
        dto.setAuthor(comment.getAuthor().getId());
        dto.setAuthorFirstName(comment.getAuthor().getFirstName());
        dto.setText(comment.getText());
        dto.setAuthorImage("/image/" + comment.getAuthor().getImage().getId());
        dto.setCreatedAt(comment.getCreatedAt());

        return dto;
    }
}
