package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepository extends MongoRepository<Catalog, String> {

}
