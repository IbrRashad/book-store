package com.rashad.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailAdd implements Serializable {
    @NotBlank(message= "Book name is required")
    @Size (max=100)
    private String name;

    @NotBlank(message = "Author name  is required")
    private String author;

    @NotNull(message = "Price is required")
    @Range(min=0 , message = "Price cannot be smaller than 0")
    private BigDecimal price;

    private String description;



}
