package com.gautama.cabinbookingbackend.core.repository;

import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinRepository extends JpaRepository<Cabin, Long> {
    List<Cabin> findByMainImageId(Long id);
}
