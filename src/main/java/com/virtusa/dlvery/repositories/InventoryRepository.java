package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    // Find inventory by product ID
    List<Inventory> findByProductID(UUID productID);

    // Find inventory by warehouse ID
    List<Inventory> findByWarehouseID(UUID warehouseID);

    // Find perishable inventory items with expiry date before the specified date
    List<Inventory> findByPerishableIsTrueAndExpiryDateBefore(LocalDate expiryDate);

    // Find damaged inventory items
    List<Inventory> findByDamagedStatusIsTrue();

    // Find inventory items with quantity in stock (quantityIn - quantityOut) greater than the specified amount
    List<Inventory> findByQuantityInGreaterThanAndQuantityOutLessThan(Long quantityIn, Long quantityOut);

    // Find inventory items by product ID and warehouse ID
    List<Inventory> findByProductIDAndWarehouseID(UUID productID, UUID warehouseID);

    // Find inventory items by product ID and perishable status
    List<Inventory> findByProductIDAndPerishable(UUID productID, boolean perishable);

    // Find inventory items by product ID and expiry date
    List<Inventory> findByProductIDAndExpiryDate(UUID productID, LocalDate expiryDate);

    // Find inventory items by warehouse ID and damaged status
    List<Inventory> findByWarehouseIDAndDamagedStatus(UUID warehouseID, boolean damagedStatus);

    // Find inventory items by perishable status and expiry date
    List<Inventory> findByPerishableAndExpiryDate(boolean perishable, LocalDate expiryDate);
   /* @Query("SELECT i FROM inventory_tbl i WHERE i.productID = :productId AND i.warehouseID = :warehouseId " +
            "AND (i.expiryDate IS NULL OR i.expiryDate >= :currentDate)")*/
   default List<Inventory> findNonExpiredInventory(@Param("productId") UUID productId,
                                                   @Param("warehouseId") UUID warehouseId,
                                                   @Param("currentDate") LocalDate currentDate) {
       // Need to custom logic to find non-expired inventory items
       List<Inventory> inventoryList = null;
       return inventoryList;
    }

    List<Inventory> findByPerishable(boolean b);

    List<Inventory> findByDamagedStatus(boolean b);

    List<Inventory> findByExpiryDateBefore(LocalDate date);

    List<Inventory> findByExpiryDateAfter(LocalDate date);

    List<Inventory> findByQuantityInEqualsAndQuantityOut(Long quantityIn, Long quantityOut);

    // Add more custom queries as needed...
}

