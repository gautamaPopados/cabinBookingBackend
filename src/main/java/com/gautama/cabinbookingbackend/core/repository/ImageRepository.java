package com.gautama.cabinbookingbackend.core.repository;

import com.gautama.cabinbookingbackend.api.dto.ImageDto;
import com.gautama.cabinbookingbackend.core.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT new com.gautama.cabinbookingbackend.api.dto.ImageDto(i.id, i.name, i.isDescription) FROM Image i WHERE i.isDescription = true")
    List<ImageDto> findAllByIsDescriptionTrue();
}
