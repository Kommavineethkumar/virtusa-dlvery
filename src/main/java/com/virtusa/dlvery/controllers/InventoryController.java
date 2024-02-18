package com.virtusa.dlvery.controllers;

import com.virtusa.dlvery.entities.Inventory;
import com.virtusa.dlvery.exceptions.InventoryNotFoundException;
import com.virtusa.dlvery.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventoryList = inventoryService.getAllInventory();
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable UUID inventoryId) {
        try {
            Inventory inventory = inventoryService.getInventoryById(inventoryId);
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } catch (InventoryNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        try {
            Inventory createdInventory = inventoryService.createInventory(inventory);
            return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable UUID inventoryId, @RequestBody Inventory inventory) {
        try {
            Inventory updatedInventory = inventoryService.updateInventory(inventoryId, inventory);
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        } catch (InventoryNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> deleteInventory(@PathVariable UUID inventoryId) {
        try {
            inventoryService.deleteInventory(inventoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InventoryNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Inventory>> getInventoryByProductId(@PathVariable UUID productId) {
        List<Inventory> inventoryList = inventoryService.getInventoryByProductId(productId);
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Inventory>> getInventoryByWarehouseId(@PathVariable UUID warehouseId) {
        List<Inventory> inventoryList = inventoryService.getInventoryByWarehouseId(warehouseId);
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @GetMapping("/perishable")
    public ResponseEntity<List<Inventory>> getPerishableInventory() {
        List<Inventory> inventoryList = inventoryService.getPerishableInventory();
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @GetMapping("/damaged")
    public ResponseEntity<List<Inventory>> getDamagedInventory() {
        List<Inventory> inventoryList = inventoryService.getDamagedInventory();
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @GetMapping("/expiring-before/{date}")
    public ResponseEntity<List<Inventory>> getInventoryExpiringBefore(@PathVariable LocalDate date) {
        List<Inventory> inventoryList = inventoryService.getInventoryExpiringBefore(date);
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @GetMapping("/expiring-after/{date}")
    public ResponseEntity<List<Inventory>> getInventoryExpiringAfter(@PathVariable LocalDate date) {
        List<Inventory> inventoryList = inventoryService.getInventoryExpiringAfter(date);
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    // Additional methods
    // Add more methods as needed based on your requirements
    // ...

    // Example: Get inventory with specific damaged status
    @GetMapping("/damaged-status/{damagedStatus}")
    public ResponseEntity<List<Inventory>> getInventoryByDamagedStatus(@PathVariable boolean damagedStatus) {
        List<Inventory> inventoryList = inventoryService.getInventoryByDamagedStatus(damagedStatus);
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    // Example: Get inventory by quantity in and quantity out
    @GetMapping("/quantity/{quantityIn}/{quantityOut}")
    public ResponseEntity<List<Inventory>> getInventoryByQuantityInAndQuantityOut(
            @PathVariable Long quantityIn, @PathVariable Long quantityOut) {
        List<Inventory> inventoryList = inventoryService.getInventoryByQuantityInAndQuantityOut(quantityIn, quantityOut);
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }
}
