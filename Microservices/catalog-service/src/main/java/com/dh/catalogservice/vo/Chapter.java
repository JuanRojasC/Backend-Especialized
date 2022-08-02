package com.dh.catalogservice.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Chapter {

    private String id;
    private String name;
    private Integer number;
    private String urlStream;
}
