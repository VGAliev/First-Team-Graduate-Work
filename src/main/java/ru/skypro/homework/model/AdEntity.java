package ru.skypro.homework.model;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
    private int price;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public AdEntity(int pk, Image image, int price, String title, String description, User author) {
        this.pk = pk;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
