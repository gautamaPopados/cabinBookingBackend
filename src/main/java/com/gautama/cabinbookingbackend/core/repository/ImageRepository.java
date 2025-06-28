package com.gautama.cabinbookingbackend.core.repository;

import com.gautama.cabinbookingbackend.core.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i.id FROM Image i WHERE i.isDescription = true")
    List<Long> findAllDescriptionImageIds();
}
