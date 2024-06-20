package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer author;
    private Integer createdAt;
    private String authorFirstName;
    private String authorImage;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private AdEntity ad;

    public CommentEntity(String text) {
        this.text = text;
    }
}
