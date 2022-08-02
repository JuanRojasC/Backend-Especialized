package com.dh.serieservice.service;

import com.dh.serieservice.model.Serie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SerieService {

    List<Serie> getAll();
    List<Serie> getAllByGenre(String genre);
    Serie getByID(String id);
    Serie save(Serie serie);
    Serie update(Serie serie);
    Boolean deleteByID(String id);
    Boolean checkGenre(String genre);

}
