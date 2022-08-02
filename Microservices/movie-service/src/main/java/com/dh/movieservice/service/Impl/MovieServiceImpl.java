package com.dh.movieservice.service.Impl;

import com.dh.movieservice.model.Genre;
import com.dh.movieservice.model.Movie;
import com.dh.movieservice.repository.MovieRepository;
import com.dh.movieservice.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAll() {
        log.info("All movies requested");
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getAllByGenre(String genre) {
        return movieRepository.getMoviesByGenre(genre);
    }

    @Override
    public Movie getByID(Long id) {
        log.info("Movie with id {} requested", id);
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return null;
        }
        return movie.get();
    }

    @Override
    public Movie save(Movie movie) {
        movie.setId(null);
        Movie m = movieRepository.save(movie);
        log.info("New Movie with id {} saved", m.getId());
        return m;
    }

    @Override
    public Movie update(Movie movie) {
        Optional<Movie> movieFound = movieRepository.findById(movie.getId());
        if (movieFound.isPresent()) {
            Movie m = movieRepository.save(movie);
            return m;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Movie> m = movieRepository.findById(id);
        if (m.isPresent()) {
            movieRepository.deleteById(id);
            log.warn("Movie with id {} deleted", id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkGenre(String genre) {
        String genreCapitalized = genre.substring(0, 1).toUpperCase() + genre.substring(1).toLowerCase();
        for (Genre g : Genre.values()) if (g.name().equals(genreCapitalized)) return true;
        return false;
    }
}
