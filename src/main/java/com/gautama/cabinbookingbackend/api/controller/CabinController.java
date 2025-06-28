package com.gautama.cabinbookingbackend.api.controller;

import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.service.CabinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cabins")
public class CabinController {

    private final CabinService cabinService;

    public CabinController(CabinService cabinService) {
        this.cabinService = cabinService;
    }

    @GetMapping
    public List<Cabin> getAllCabins() {
        return cabinService.getAllCabins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cabin> getCabinById(@PathVariable Long id) {
        return cabinService.getCabinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Cabin> createCabin(
            @RequestPart("cabin") Cabin cabin,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        if (image != null && !image.isEmpty()) {
            cabin.setImage(image.getBytes());
        }
        return ResponseEntity.status(201).body(cabinService.createCabin(cabin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cabin> updateCabin(@PathVariable Long id, @RequestBody Cabin cabin) {
        return cabinService.updateCabin(id, cabin)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCabin(@PathVariable Long id) {
        cabinService.deleteCabin(id);
        return ResponseEntity.noContent().build();
    }
}