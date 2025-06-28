package com.gautama.cabinbookingbackend.api.controller;

import com.gautama.cabinbookingbackend.api.dto.CabinCreateDto;
import com.gautama.cabinbookingbackend.api.dto.CabinDto;
import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.Image;
import com.gautama.cabinbookingbackend.core.service.CabinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cabins")
@RequiredArgsConstructor
public class CabinController {

    private final CabinService cabinService;

    @GetMapping
    public List<CabinDto> getAllCabins() {
        return cabinService.getAllCabins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabinDto> getCabinById(@PathVariable Long id) {
        return cabinService.getCabinById(id)
                .map(ResponseEntity::ok)
                .orElseThrow( ()-> new NoSuchElementException("Cabin with id " + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<CabinDto> createCabin(@RequestBody CabinCreateDto request) {
        return ResponseEntity.status(201).body(cabinService.createCabin(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCabin(@PathVariable Long id) {
        cabinService.deleteCabin(id);
        return ResponseEntity.noContent().build();
    }
}