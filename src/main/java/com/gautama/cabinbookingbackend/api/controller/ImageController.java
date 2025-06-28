package com.gautama.cabinbookingbackend.api.controller;

import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.Image;
import com.gautama.cabinbookingbackend.core.repository.CabinRepository;
import com.gautama.cabinbookingbackend.core.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.redis.connection.ReactiveStreamCommands.AddStreamRecord.body;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageRepository imageRepository;
    private final CabinRepository cabinRepository;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Image> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "isDescription", defaultValue = "false") boolean isDescription
    ) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image.setDescription(isDescription);

        return ResponseEntity.ok(imageRepository.save(image));
    }

    @GetMapping
    public ResponseEntity<List<Long>> getAllImagesIds() {
        return ResponseEntity.ok(imageRepository.findAll().stream().map(Image::getId).toList());
    }

    @GetMapping("/description")
    public ResponseEntity<List<Long>> getAllDescriptionImagesIds() {
        return ResponseEntity.ok(imageRepository.findAllDescriptionImageIds());
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        Image img = imageRepository.findById(id).orElseThrow();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + img.getId() + "\"")
                .body(img.getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") Long id) {
        List<Cabin> cabins = cabinRepository.findByMainImageId(id);
        for (Cabin cabin : cabins) {
            cabin.setMainImageId(null);
            cabinRepository.save(cabin);
        }
        imageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
