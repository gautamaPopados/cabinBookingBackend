package com.gautama.cabinbookingbackend.core.service;

import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.repository.CabinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabinService {

    private final CabinRepository cabinRepository;

    public CabinService(CabinRepository cabinRepository) {
        this.cabinRepository = cabinRepository;
    }

    public List<Cabin> getAllCabins() {
        return cabinRepository.findAll();
    }

    public Optional<Cabin> getCabinById(Long id) {
        return cabinRepository.findById(id);
    }

    public Cabin createCabin(Cabin cabin) {
        return cabinRepository.save(cabin);
    }

    public Optional<Cabin> updateCabin(Long id, Cabin updatedCabin) {
        return cabinRepository.findById(id).map(cabin -> {
            cabin.setName(updatedCabin.getName());
            cabin.setLocation(updatedCabin.getLocation());
            cabin.setDescription(updatedCabin.getDescription());
            cabin.setPrice(updatedCabin.getPrice());
            cabin.setImage(updatedCabin.getImage());
            return cabinRepository.save(cabin);
        });
    }

    public void deleteCabin(Long id) {
        cabinRepository.deleteById(id);
    }
}