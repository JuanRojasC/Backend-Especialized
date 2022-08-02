package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.service.SerieFeignRepository;
import com.dh.catalogservice.vo.Movie;
import com.dh.catalogservice.vo.Serie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SerieServiceImpl implements SerieFeignRepository {

    private final SerieFeignRepository serieFeignRepository;

    @Autowired
    public SerieServiceImpl(SerieFeignRepository serieFeignRepository) {
        this.serieFeignRepository = serieFeignRepository;
    }

    @CircuitBreaker(name = "series", fallbackMethod = "serieFallbackMethod")
    @Override
    public List<Serie> getAllSeriesByGenre(String genre) {
        return serieFeignRepository.getAllSeriesByGenre(genre);
    }

    /* ---------------------------------------- CIRCUIT BREAKER ---------------------------------------- */
    private List<Movie> serieFallbackMethod(Exception ex) {
        log.error("Circuit breaker for series active");
        return new ArrayList<>();
    }
}
