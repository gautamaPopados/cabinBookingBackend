package com.gautama.cabinbookingbackend.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Cabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String description;
    private BigDecimal price;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
}