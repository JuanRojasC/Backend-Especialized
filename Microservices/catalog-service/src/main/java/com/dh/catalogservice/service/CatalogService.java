package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.vo.Movie;
import com.dh.catalogservice.vo.Serie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    List<Catalog> getAll();
    Catalog getByGenre(String genre);
    Catalog update(Catalog catalog);
    Catalog save(Catalog catalog);
    Catalog addMovie(Movie movie);
    Catalog addSerie(Serie serie);
}
