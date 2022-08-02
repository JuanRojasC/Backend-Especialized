package com.dh.serieservice.repository;

import com.dh.serieservice.model.Season;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeasonRepository extends MongoRepository<Season, String> {

}
