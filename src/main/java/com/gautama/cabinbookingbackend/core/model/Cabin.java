package com.gautama.cabinbookingbackend.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    private Long mainImageId;

    @ElementCollection
    @CollectionTable(
            name = "cabin_additional_images",
            joinColumns = @JoinColumn(name = "cabin_id")
    )
    @Column(name = "image_id")
    private List<Long> additionalImageIds = new ArrayList<>();
}