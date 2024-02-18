package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Inventory;
import com.virtusa.dlvery.exceptions.InventoryNotFoundException;
import com.virtusa.dlvery.repositories.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventory() {
        LOGGER.info("Fetching all inventory items");
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(UUID inventoryId) {
        LOGGER.info("Fetching inventory item with ID: {}", inventoryId);
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory item not found with ID: " + inventoryId));
    }

    @Override
    public List<Inventory> getInventoryByProductId(UUID productId) {
        LOGGER.info("Fetching inventory items by Product ID: {}", productId);
        return inventoryRepository.findByProductID(productId);
    }

    @Override
    public List<Inventory> getInventoryByWarehouseId(UUID warehouseId) {
        LOGGER.info("Fetching inventory items by Warehouse ID: {}", warehouseId);
        return inventoryRepository.findByWarehouseID(warehouseId);
    }

    @Override
    public List<Inventory> getPerishableInventory() {
        LOGGER.info("Fetching perishable inventory items");
        return inventoryRepository.findByPerishable(true);
    }

    @Override
    public List<Inventory> getDamagedInventory() {
        LOGGER.info("Fetching damaged inventory items");
        return inventoryRepository.findByDamagedStatus(true);
    }

    @Override
    public List<Inventory> getInventoryExpiringBefore(LocalDate date) {
        LOGGER.info("Fetching inventory items expiring before: {}", date);
        return inventoryRepository.findByExpiryDateBefore(date);
    }

    @Override
    public List<Inventory> getInventoryExpiringAfter(LocalDate date) {
        LOGGER.info("Fetching inventory items expiring after: {}", date);
        return inventoryRepository.findByExpiryDateAfter(date);
    }

    // Implement other service methods and CRUD operations
    // ...

    @Override
    public Inventory createInventory(Inventory inventory) {
        LOGGER.info("Creating a new inventory item");
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(UUID inventoryId, Inventory inventory) {
        LOGGER.info("Updating inventory item with ID: {}", inventoryId);
        Inventory existingInventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory item not found with ID: " + inventoryId));

        // Update inventory details
        existingInventory.setProductID(inventory.getProductID());
        existingInventory.setQuantityIn(inventory.getQuantityIn());
        existingInventory.setQuantityOut(inventory.getQuantityOut());
        existingInventory.setDamagedStatus(inventory.isDamagedStatus());
        existingInventory.setPerishable(inventory.isPerishable());
        existingInventory.setExpiryDate(inventory.getExpiryDate());
        existingInventory.setWarehouseID(inventory.getWarehouseID());
        existingInventory.setFieldName(inventory.getFieldName());

        return inventoryRepository.save(existingInventory);
    }

    @Override
    public void deleteInventory(UUID inventoryId) {
        LOGGER.info("Deleting inventory item with ID: {}", inventoryId);
        inventoryRepository.deleteById(inventoryId);
    }

    @Override
    public List<Inventory> getInventoryByDamagedStatus(boolean damagedStatus) {
        LOGGER.info("Fetching inventory items by damaged status: {}", damagedStatus);
        return inventoryRepository.findByDamagedStatus(damagedStatus);
    }

    @Override
    public List<Inventory> getInventoryByQuantityInAndQuantityOut(Long quantityIn, Long quantityOut) {
        LOGGER.info("Fetching inventory items by quantity in: {} and quantity out: {}", quantityIn, quantityOut);
        return inventoryRepository.findByQuantityInEqualsAndQuantityOut(quantityIn, quantityOut);
    }
}