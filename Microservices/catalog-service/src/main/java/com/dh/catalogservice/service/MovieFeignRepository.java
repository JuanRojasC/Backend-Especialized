package com.dh.catalogservice.service;

import com.dh.catalogservice.vo.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
@FeignClient(name = "movie-service")
public interface MovieFeignRepository {

    @GetMapping("/movies/{genre}")
    List<Movie> getAllMoviesByGenre(@PathVariable String genre);
}
