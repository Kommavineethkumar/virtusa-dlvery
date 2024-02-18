package com.virtusa.dlvery.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products_tbl")
public class Products {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID productId;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotNull(message = "Category ID is required")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    // Other necessary fields, constructors, getters, and setters
}

