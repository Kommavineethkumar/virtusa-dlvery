package com.virtusa.dlvery.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse_tbl")

public class Warehouse {
    @Id
    private UUID warehouseID;

    @NotBlank(message = "WarehouseName is required")
    @Size(max = 255, message = "WarehouseName must be less than or equal to 255 characters")
    private String warehouseName;

    @NotBlank(message = "WarehouseLocation is required")
    @Size(max = 255, message = "WarehouseLocation must be less than or equal to 255 characters")
    private String warehouseLocation;

}
