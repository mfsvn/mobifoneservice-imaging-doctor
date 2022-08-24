package com.tunv.ham10000.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "image")
@Getter
@Setter
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;

    @Column(name = "img_id")
    private String imgId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "predicted")
    private Long predicted;

    @Column(name = "certainty_percent")
    private Long certaintyPercent;
}
