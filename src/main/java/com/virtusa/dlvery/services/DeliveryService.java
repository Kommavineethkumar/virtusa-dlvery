package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Delivery;
import com.virtusa.dlvery.enums.DeliveryStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    List<Delivery> getAllDeliveries();
    Delivery getDeliveryById(UUID deliveryId);
    List<Delivery> getDeliveriesByCustomerName(String customerName);
    List<Delivery> getDeliveriesByDeliveryStatus(DeliveryStatus deliveryStatus);
    List<Delivery> getDeliveriesByDeliveryDate(LocalDate deliveryDate);
    List<Delivery> getDeliveriesByProductId(UUID productId);
    List<Delivery> getDeliveriesByAgentId(UUID agentId);
    Delivery createDelivery(Delivery delivery);
    Delivery updateDelivery(UUID deliveryId, Delivery delivery);
    void deleteDelivery(UUID deliveryId);
}

