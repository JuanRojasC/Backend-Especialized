package com.dh.serieservice.service.Impl;

import com.dh.serieservice.model.Genre;
import com.dh.serieservice.model.Season;
import com.dh.serieservice.model.Serie;
import com.dh.serieservice.repository.SerieRepository;
import com.dh.serieservice.service.SeasonService;
import com.dh.serieservice.service.SerieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SerieServiceImpl implements SerieService {

    private final SerieRepository serieRepository;
    private final SeasonService seasonService;

    @Autowired
    public SerieServiceImpl(SerieRepository serieRepository, SeasonService seasonService) {
        this.serieRepository = serieRepository;
        this.seasonService = seasonService;
    }

    @Override
    public List<Serie> getAll() {
        log.info("All series were requested");
        return serieRepository.findAll();
    }

    @Override
    public List<Serie> getAllByGenre(String genre) {
        return serieRepository.getSerieByGenre(genre);
    }

    @Override
    public Serie getByID(String id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if (serie.isEmpty()) {
            return null;
        }
        log.info("Serie with id {} required", id);
        return serie.get();
    }

    @Override
    public Serie save(Serie serie) {
        serie.setId(null);
        List<Season> seasons = new ArrayList<>();
        serie.getSeasons().forEach(s -> seasons.add(seasonService.save(s)));
        serie.setSeasons(seasons);
        Serie s = serieRepository.save(serie);
        log.info("New Serie with id {} saved", s.getId());
        return s;
    }

    @Override
    public Serie update(Serie serie) {
        Optional<Serie> s = serieRepository.findById(serie.getId());
        if (s.isEmpty()) {
            return null;
        }
        log.info("Serie with id {} updated", serie.getId());
        return serieRepository.save(s.get());
    }

    @Override
    public Boolean deleteByID(String id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if (serie.isPresent()) {
            serieRepository.deleteById(id);
            log.warn("Serie with id {} deleted", id);
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
