package com.dh.serieservice.service;

import com.dh.serieservice.model.Chapter;
import com.dh.serieservice.model.Chapter;
import org.springframework.stereotype.Service;

@Service
public interface ChapterService {
    Chapter getByID(String id);
    Chapter save(Chapter chapter);
    Chapter update(Chapter chapter);
    Boolean deleteByID(String id);
}
