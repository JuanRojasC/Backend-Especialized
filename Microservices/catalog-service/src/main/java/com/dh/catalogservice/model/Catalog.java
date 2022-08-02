package com.dh.catalogservice.model;

import com.dh.catalogservice.vo.Movie;
import com.dh.catalogservice.vo.Serie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "catalogs")
public class Catalog {

    @Id
    private String genre;
    private List<Movie> movies;
    private List<Serie> series;
}
