package com.svalero.readify.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column
    private String ISBN;

    @Column
    private Boolean available;

    @Column
    private float pageCount;
}