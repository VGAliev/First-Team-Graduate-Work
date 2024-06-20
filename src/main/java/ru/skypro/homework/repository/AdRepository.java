package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    @Override
    Optional<AdEntity> findById(Integer id);
    Optional<CommentEntity> findCommentById(Integer id);
}
