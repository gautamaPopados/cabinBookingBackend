package com.gautama.cabinbookingbackend.core.repository;

import com.gautama.cabinbookingbackend.core.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
