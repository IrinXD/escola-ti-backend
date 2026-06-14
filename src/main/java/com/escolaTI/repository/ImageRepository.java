package com.escolaTI.repository;

import com.escolaTI.model.ImageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageDocument, String> {
}
