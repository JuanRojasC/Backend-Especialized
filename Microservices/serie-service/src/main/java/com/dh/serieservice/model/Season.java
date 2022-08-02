package com.dh.serieservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "seasons")
public class Season {

    @Id
    private String id;
    private Integer seasonNumber;
    @DBRef
    private List<Chapter> chapters;

}
