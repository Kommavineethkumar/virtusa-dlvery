package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Warehouse;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {
    List<Warehouse> getAllWarehouses();
    Warehouse getWarehouseById(UUID warehouseId);
    List<Warehouse> getWarehousesByName(String warehouseName);
    List<Warehouse> getWarehousesByLocation(String warehouseLocation);
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse updateWarehouse(UUID warehouseId, Warehouse warehouse);
    void deleteWarehouse(UUID warehouseId);
}
