package com.dh.catalogservice.service;

import com.dh.catalogservice.vo.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
@FeignClient(name = "serie-service")
public interface SerieFeignRepository {

    @GetMapping("/{genre}")
    List<Serie> getAllSeriesByGenre(@PathVariable String genre);
}
