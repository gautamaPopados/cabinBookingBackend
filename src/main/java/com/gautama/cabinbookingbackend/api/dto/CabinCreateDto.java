package com.gautama.cabinbookingbackend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinCreateDto {
    private String name;
    private Integer numberOfBeds;
    private Integer area;
    private BigDecimal price;
    private String description;
    private Long mainImageId;
    private List<Long> additionalImageIds;
}