package com.gautama.cabinbookingbackend.core.service;

import com.gautama.cabinbookingbackend.api.dto.CabinCreateDto;
import com.gautama.cabinbookingbackend.api.dto.CabinDto;
import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.Image;
import com.gautama.cabinbookingbackend.core.repository.CabinRepository;
import com.gautama.cabinbookingbackend.core.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CabinService {

    private final CabinRepository cabinRepository;
    private final ImageRepository imageRepository;

    public List<CabinDto> getAllCabins() {
        return cabinRepository.findAll().stream().map(CabinDto::new).toList();
    }

    public Optional<CabinDto> getCabinById(Long id) {
        Cabin cabin = cabinRepository.findById(id).orElse(null);
        return Optional.of(new CabinDto(cabin));
    }

    public CabinDto createCabin(CabinCreateDto request) {
        Cabin cabin = new Cabin();
        cabin.setName(request.getName());
        cabin.setDescription(request.getDescription());
        cabin.setNumberOfBeds(request.getNumberOfBeds());
        cabin.setArea(request.getArea());
        cabin.setPrice(request.getPrice());
        cabin.setMainImageId(request.getMainImageId());
        cabin.setAdditionalImageIds(request.getAdditionalImageIds());

        Cabin saved = cabinRepository.save(cabin);
        return new CabinDto(saved);
    }

    public void deleteCabin(Long id) {
        cabinRepository.deleteById(id);
    }
}