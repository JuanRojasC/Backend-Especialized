package com.dh.catalogservice.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class Season {
    private String id;
    private Integer seasonNumber;
    private List<Chapter> chapters;
}
