package ru.skypro.homework.entity;





import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath; // В нем будем хранить путь до файла на вашем диске.
    private long fileSize; // Это поле содержит информацию о размере файла в байтах.
    private String mediaType; // Тип файла.
    @Lob
    private byte[] data; // В этом поле хранится сама информация о файле, представленная в массиве байтов

    // TODO при создании класса Объявления, добавить сюда поле
    @ManyToOne
    private UserEntity user;

    public Image(Long id, String filePath, long fileSize, String mediaType, byte[] data, UserEntity user) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.data = data;
        this.user = user;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return fileSize == image.fileSize && Objects.equals(id, image.id) && Objects.equals(filePath, image.filePath) && Objects.equals(mediaType, image.mediaType) && Arrays.equals(data, image.data) && Objects.equals(user, image.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, user);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", user=" + user +
                '}';
    }
}
