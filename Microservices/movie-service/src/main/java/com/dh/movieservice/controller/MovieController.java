package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final RestTemplate restTemplate;
    private final MovieSender movieSender;

    @Autowired
    public MovieController(MovieService movieService, RestTemplate restTemplate, MovieSender movieSender) {
        this.movieService = movieService;
        this.restTemplate = restTemplate;
        this.movieSender = movieSender;
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<?>> getAllMoviesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(movieService.getAllByGenre(genre));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getByID(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return this.validateLFields(result);
        }
        if (!movieService.checkGenre(movie.getGenre())) return ResponseEntity.unprocessableEntity().body("Genre does not exist");
        movieSender.send(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
        Movie m = movieService.update(movie);
        if (m == null) {
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.ok(m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        if (movieService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    /* ---------------------------------------------------- VALIDATION FUNCTIONS ---------------------------------------------------- */
    public ResponseEntity<Object> validateLFields(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), String.format("Field %s %s", fieldError.getField(), fieldError.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }
}
