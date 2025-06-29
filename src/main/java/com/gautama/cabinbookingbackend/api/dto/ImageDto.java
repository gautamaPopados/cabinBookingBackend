package com.gautama.cabinbookingbackend.api.dto;

import com.gautama.cabinbookingbackend.core.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDto {
    Long id;
    String name;
    Boolean isDescription;

    public ImageDto(Image image) {
        this.id = image.getId();
        this.name = image.getName();
        this.isDescription = image.isDescription();
    }
}
