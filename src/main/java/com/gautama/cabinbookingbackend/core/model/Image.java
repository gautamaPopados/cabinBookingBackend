package com.gautama.cabinbookingbackend.core.model;

import com.gautama.cabinbookingbackend.api.enums.EntityType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;

    private boolean isDescription;

}
