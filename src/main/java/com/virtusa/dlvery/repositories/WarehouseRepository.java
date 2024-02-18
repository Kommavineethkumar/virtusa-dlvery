package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {

    List<Warehouse> findByWarehouseName(String warehouseName);

    List<Warehouse> findByWarehouseLocation(String warehouseLocation);

    List<Warehouse> findByWarehouseNameAndWarehouseLocation(String warehouseName, String warehouseLocation);

    List<Warehouse> findByWarehouseNameContaining(String partialWarehouseName);

    List<Warehouse> findByWarehouseLocationContaining(String partialWarehouseLocation);

    List<Warehouse> findByWarehouseNameAndWarehouseLocationContaining(String warehouseName, String partialWarehouseLocation);

    List<Warehouse> findByWarehouseNameContainingAndWarehouseLocationContaining(String partialWarehouseName, String partialWarehouseLocation);

    // Add more custom query methods as needed

}
