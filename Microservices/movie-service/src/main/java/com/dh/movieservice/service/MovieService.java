package com.dh.movieservice.service;

import com.dh.movieservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> getAll();
    List<Movie> getAllByGenre(String genre);
    Movie getByID(Long id);
    Movie save(Movie movie);
    Movie update(Movie movie);
    Boolean delete(Long id);
    Boolean checkGenre(String genre);
}
