package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.repository.CatalogRepository;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.vo.Movie;
import com.dh.catalogservice.vo.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Catalog> getAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog getByGenre(String genre) {
        Optional<Catalog> catalog = catalogRepository.findById(genre);
        if (catalog.isEmpty()) {
            return null;
        }
        return catalog.get();
    }

    @Override
    public Catalog update(Catalog catalog) {
        Optional<Catalog> c = catalogRepository.findById(catalog.getGenre());
        if (c.isEmpty()) {
            return null;
        }
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog save(Catalog catalog) {
        if (catalog.getGenre().isBlank()) {
            return null;
        }
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog addMovie(Movie movie) {
        Catalog catalog = getByGenre(movie.getGenre());
        if (catalog == null) {
            Catalog newCatalog = new Catalog(movie.getGenre(), List.of(movie), new ArrayList<>());
            return save(newCatalog);
        }
        catalog.getMovies().add(movie);
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog addSerie(Serie serie) {
        Catalog catalog = getByGenre(serie.getGenre());
        if (catalog == null) {
            Catalog newCatalog = new Catalog(serie.getGenre(), new ArrayList<>(), List.of(serie));
            return save(newCatalog);
        }
        catalog.getSeries().add(serie);
        return catalogRepository.save(catalog);
    }
}
