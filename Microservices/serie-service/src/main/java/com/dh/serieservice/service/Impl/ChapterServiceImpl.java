package com.dh.serieservice.service.Impl;

import com.dh.serieservice.model.Chapter;
import com.dh.serieservice.repository.ChapterRepository;
import com.dh.serieservice.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;

    @Autowired
    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }


    @Override
    public Chapter getByID(String id) {
        Optional<Chapter> chapter = chapterRepository.findById(id);
        if (chapter.isEmpty()) {
            return null;
        }
        return chapter.get();
    }

    @Override
    public Chapter save(Chapter chapter) {
        chapter.setId(null);
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter update(Chapter chapter) {
        Optional<Chapter> c = chapterRepository.findById(chapter.getId());
        if (c.isEmpty()) {
            return null;
        }
        return chapterRepository.save(chapter);
    }

    @Override
    public Boolean deleteByID(String id) {
        Optional<Chapter> c = chapterRepository.findById(id);
        if (c.isPresent()) {
            chapterRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
