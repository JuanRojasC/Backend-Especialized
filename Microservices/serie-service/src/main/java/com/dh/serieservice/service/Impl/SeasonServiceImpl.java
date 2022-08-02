package com.dh.serieservice.service.Impl;

import com.dh.serieservice.model.Chapter;
import com.dh.serieservice.model.Season;
import com.dh.serieservice.repository.ChapterRepository;
import com.dh.serieservice.repository.SeasonRepository;
import com.dh.serieservice.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository, ChapterRepository chapterRepository) {
        this.seasonRepository = seasonRepository;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public Season getByID(String id) {
        Optional<Season> season = seasonRepository.findById(id);
        if (season.isEmpty()) {
            return null;
        }
        return season.get();
    }

    @Override
    public Season save(Season season) {
        season.setId(null);
        List<Chapter> chapters = chapterRepository.saveAll(season.getChapters());
        season.setChapters(chapters);
        return seasonRepository.save(season);
    }

    @Override
    public Season update(Season season) {
        Optional<Season> s = seasonRepository.findById(season.getId());
        if (s.isEmpty()) {
            return null;
        }
        return seasonRepository.save(season);
    }

    @Override
    public Boolean deleteByID(String id) {
        Optional<Season> season = seasonRepository.findById(id);
        if (season.isEmpty()) {
            return false;
        }
        seasonRepository.deleteById(id);
        return true;
    }
}
