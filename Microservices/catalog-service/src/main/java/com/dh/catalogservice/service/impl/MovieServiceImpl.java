package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.service.MovieFeignRepository;
import com.dh.catalogservice.vo.Movie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieServiceImpl implements MovieFeignRepository {

    private final MovieFeignRepository movieFeignRepository;

    @Autowired
    public MovieServiceImpl(MovieFeignRepository movieFeignRepository) {
        this.movieFeignRepository = movieFeignRepository;
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "moviesFallbackMethod")
    @Retry(name = "movies")
    @Override
    public List<Movie> getAllMoviesByGenre(String genre) {
        return movieFeignRepository.getAllMoviesByGenre(genre);
    }

    /* ---------------------------------------- CIRCUIT BREAKER ---------------------------------------- */
    private List<Movie> moviesFallbackMethod(Exception ex) {
        log.error("Circuit breaker for movies active");
        return new ArrayList<>();
    }
}
