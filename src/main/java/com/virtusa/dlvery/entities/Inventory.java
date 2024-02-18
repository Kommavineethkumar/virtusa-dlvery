package com.virtusa.dlvery.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory_tbl")
public class Inventory {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "inventory_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID inventoryID;

    @NotNull(message = "Product ID is required")
    @Column(name = "product_id", nullable = false)
    private UUID productID;

    @NotNull(message = "Quantity In is required")
    @Min(value = 0, message = "Quantity In must be greater than or equal to 0")
    @Column(name = "quantity_in", nullable = false)
    private Long quantityIn;

    @NotNull(message = "Quantity Out is required")
    @Min(value = 0, message = "Quantity Out must be greater than or equal to 0")
    @Column(name = "quantity_out", nullable = false)
    private Long quantityOut;

    @NotNull(message = "Damaged status is required")
    @Column(name = "damaged_status", nullable = false)
    private boolean damagedStatus;

    @NotNull(message = "Perishable status is required")
    @Column(name = "perishable", nullable = false)
    private boolean perishable;

    @FutureOrPresent(message = "Expiry Date must be a future or present date")
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @NotNull(message = "Warehouse ID is required")
    @Column(name = "warehouse_id", nullable = false)
    private UUID warehouseID;

    // Add @NotBlank validation for string fields
    @NotBlank(message = "Field can not be blank")
    @Column(name = "field_name", nullable = false)
    private String fieldName;

    // Other necessary methods, constructors, getters, and setters
}

