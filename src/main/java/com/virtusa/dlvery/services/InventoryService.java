package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Inventory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface InventoryService {
    List<Inventory> getAllInventory();
    Inventory getInventoryById(UUID inventoryId);
    List<Inventory> getInventoryByProductId(UUID productId);
    List<Inventory> getInventoryByWarehouseId(UUID warehouseId);
    List<Inventory> getPerishableInventory();
    List<Inventory> getDamagedInventory();
    List<Inventory> getInventoryExpiringBefore(LocalDate date);
    List<Inventory> getInventoryExpiringAfter(LocalDate date);
    // Add more service methods as needed
    // ...

    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(UUID inventoryId, Inventory inventory);
    void deleteInventory(UUID inventoryId);

    List<Inventory> getInventoryByDamagedStatus(boolean damagedStatus);

    List<Inventory> getInventoryByQuantityInAndQuantityOut(Long quantityIn, Long quantityOut);
}

