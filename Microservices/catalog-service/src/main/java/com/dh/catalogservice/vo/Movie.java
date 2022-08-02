package com.dh.catalogservice.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Movie {

    private Long id;
    private String name;
    private String genre;
    private String ulrStream;

}
