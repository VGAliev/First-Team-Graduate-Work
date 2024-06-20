package ru.skypro.homework.entity;





import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long fileSize; // Это поле содержит информацию о размере файла в байтах.
    private String mediaType; // Тип файла.
    @Lob
    private byte[] data; // В этом поле хранится сама информация о файле, представленная в массиве байтов

    // TODO при создании класса Объявления, добавить сюда поле
    @ManyToOne
    private UserEntity user;


}
