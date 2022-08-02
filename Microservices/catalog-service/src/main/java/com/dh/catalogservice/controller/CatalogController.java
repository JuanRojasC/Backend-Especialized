package com.dh.catalogservice.controller;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogController {

    private final CatalogService catalogService;
    private final RestTemplate restTemplate;

    @Autowired
    public CatalogController(CatalogService catalogService, RestTemplate restTemplate) {
        this.catalogService = catalogService;
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public ResponseEntity<List<Catalog>> getAllCatalogs() {
        return ResponseEntity.ok(catalogService.getAll());
    }

    @GetMapping("/{genre}")
    public ResponseEntity<Catalog> getCatalogByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(catalogService.getByGenre(genre));
    }
}
