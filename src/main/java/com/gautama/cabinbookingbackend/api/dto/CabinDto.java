package com.gautama.cabinbookingbackend.api.dto;

import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long mainImageId;
    private List<Long> additionalImageIds;

    public CabinDto(Cabin cabin) {

        this.id = cabin.getId();
        this.name = cabin.getName();
        this.description = cabin.getDescription();
        this.price = cabin.getPrice();
        this.mainImageId = cabin.getMainImageId();
        this.additionalImageIds = cabin.getAdditionalImageIds();
    }
}