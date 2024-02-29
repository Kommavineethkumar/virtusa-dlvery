package com.virtusa.dlvery.Inventory.DTO;

import com.virtusa.dlvery.Inventory.Enum.MovementType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class InventoryRequestDTO {

    private UUID movementId;
    private UUID productId;
    private int quantityIn;
    private int quantityOut;
    private MovementType movementType;
    private String reason;
    private LocalDate date;
    private UUID warehouseId;

}
