package com.rashad.bookstore.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private Integer id;
    private String userName;
    private List<String> roles;

}
