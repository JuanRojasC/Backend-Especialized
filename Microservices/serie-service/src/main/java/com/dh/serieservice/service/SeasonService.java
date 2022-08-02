package com.dh.serieservice.service;

import com.dh.serieservice.model.Season;
import org.springframework.stereotype.Service;

@Service
public interface SeasonService {
    Season getByID(String id);
    Season save(Season season);
    Season update(Season season);
    Boolean deleteByID(String id);
}
