package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Warehouse;
import com.virtusa.dlvery.exceptions.WarehouseNotFoundException;
import com.virtusa.dlvery.repositories.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> getAllWarehouses() {
        LOGGER.info("Fetching all warehouses");
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getWarehouseById(UUID warehouseId) {
        LOGGER.info("Fetching warehouse with ID: {}", warehouseId);
        return warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found with ID: " + warehouseId));
    }

    @Override
    public List<Warehouse> getWarehousesByName(String warehouseName) {
        LOGGER.info("Fetching warehouses by name: {}", warehouseName);
        return warehouseRepository.findByWarehouseName(warehouseName);
    }

    @Override
    public List<Warehouse> getWarehousesByLocation(String warehouseLocation) {
        LOGGER.info("Fetching warehouses by location: {}", warehouseLocation);
        return warehouseRepository.findByWarehouseLocation(warehouseLocation);
    }

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        LOGGER.info("Creating a new warehouse");
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse updateWarehouse(UUID warehouseId, Warehouse warehouse) {
        LOGGER.info("Updating warehouse with ID: {}", warehouseId);
        Warehouse existingWarehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found with ID: " + warehouseId));

        existingWarehouse.setWarehouseName(warehouse.getWarehouseName());
        existingWarehouse.setWarehouseLocation(warehouse.getWarehouseLocation());

        return warehouseRepository.save(existingWarehouse);
    }

    @Override
    public void deleteWarehouse(UUID warehouseId) {
        LOGGER.info("Deleting warehouse with ID: {}", warehouseId);
        warehouseRepository.deleteById(warehouseId);
    }
}
