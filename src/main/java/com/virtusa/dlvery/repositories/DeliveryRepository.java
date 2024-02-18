package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.Delivery;
import com.virtusa.dlvery.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

    List<Delivery> findByCustomerName(String customerName);

    List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus);

    List<Delivery> findByDeliveryDate(LocalDate deliveryDate);

    List<Delivery> findByProduct_ProductId(UUID productId);

    List<Delivery> findByDeliveryAgent_AgentId(UUID agentId);

    List<Delivery> findByDeliveryStatusAndDeliveryDate(DeliveryStatus deliveryStatus, LocalDate deliveryDate);

    List<Delivery> findByProduct_ProductIdAndDeliveryStatus(UUID productId, DeliveryStatus deliveryStatus);

    List<Delivery> findByProduct_ProductIdAndDeliveryAgent_AgentId(UUID productId, UUID agentId);

    List<Delivery> findByDeliveryStatusAndDeliveryAgent_AgentId(DeliveryStatus deliveryStatus, UUID agentId);

    List<Delivery> findByProduct_ProductIdAndDeliveryDate(UUID productId, LocalDate deliveryDate);

    List<Delivery> findByDeliveryAgent_AgentIdAndDeliveryDate(UUID agentId, LocalDate deliveryDate);

    List<Delivery> findByCustomerNameAndDeliveryStatus(String customerName, DeliveryStatus deliveryStatus);

    List<Delivery> findByCustomerNameAndDeliveryDate(String customerName, LocalDate deliveryDate);

    List<Delivery> findByCustomerNameAndProduct_ProductId(String customerName, UUID productId);

    // Add more custom query methods as needed

}
