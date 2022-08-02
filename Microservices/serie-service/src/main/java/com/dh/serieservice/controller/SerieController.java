package com.dh.serieservice.controller;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.queue.SerieSender;
import com.dh.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("series")
public class SerieController {

    private final SerieService serieService;
    private final RestTemplate restTemplate;
    private final SerieSender serieSender;

    @Autowired
    public SerieController(SerieService serieService, RestTemplate restTemplate, SerieSender serieSender) {
        this.serieService = serieService;
        this.restTemplate = restTemplate;
        this.serieSender = serieSender;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllSeries() {
        return ResponseEntity.ok(serieService.getAll());
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<?>> getAllSeriesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(serieService.getAllByGenre(genre));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getSerieByID(@PathVariable String id) {
        Serie serie = serieService.getByID(id);
        if (serie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serie);
    }

    @PostMapping()
    public ResponseEntity<?> saveSerie(@RequestBody Serie serie, BindingResult result) {
        if (result.hasErrors()) {
            return validateLFields(result);
        }
        if (!serieService.checkGenre(serie.getGenre())) return ResponseEntity.unprocessableEntity().body("Genre does not exist");
        serieSender.send(serie);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSerie(@RequestBody Serie serie, @PathVariable String id) {
        serie.setId(id);
        Serie s = serieService.update(serie);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSerie(@PathVariable String id) {
        if (serieService.deleteByID(id) ){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /* ---------------------------------------------------- VALIDATION FUNCTIONS ---------------------------------------------------- */
    public ResponseEntity<Object> validateLFields(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), String.format("Field %s %s", fieldError.getField(), fieldError.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }
}
