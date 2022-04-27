package com.rashad.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Book")
@Builder

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;
    private String name;
    private String author;
    private BigDecimal price;
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;

    @Lob
    private byte[] image;

}
