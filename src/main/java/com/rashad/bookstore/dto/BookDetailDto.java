package com.rashad.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailDto {
    private String publisherName;
    private String name;
    private String author;
    private BigDecimal price;
    private String description;
    private byte[] image;


}
