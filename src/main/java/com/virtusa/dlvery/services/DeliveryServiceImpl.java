package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Delivery;
import com.virtusa.dlvery.enums.DeliveryStatus;
import com.virtusa.dlvery.exceptions.DeliveryNotFoundException;
import com.virtusa.dlvery.repositories.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getAllDeliveries() {
        LOGGER.info("Fetching all deliveries");
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery getDeliveryById(UUID deliveryId) {
        LOGGER.info("Fetching delivery with ID: {}", deliveryId);
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found with ID: " + deliveryId));
    }

    @Override
    public List<Delivery> getDeliveriesByCustomerName(String customerName) {
        LOGGER.info("Fetching deliveries by customer name: {}", customerName);
        return deliveryRepository.findByCustomerName(customerName);
    }

    @Override
    public List<Delivery> getDeliveriesByDeliveryStatus(DeliveryStatus deliveryStatus) {
        LOGGER.info("Fetching deliveries by delivery status: {}", deliveryStatus);
        return deliveryRepository.findByDeliveryStatus(deliveryStatus);
    }

    @Override
    public List<Delivery> getDeliveriesByDeliveryDate(LocalDate deliveryDate) {
        LOGGER.info("Fetching deliveries by delivery date: {}", deliveryDate);
        return deliveryRepository.findByDeliveryDate(deliveryDate);
    }

    @Override
    public List<Delivery> getDeliveriesByProductId(UUID productId) {
        LOGGER.info("Fetching deliveries by product ID: {}", productId);
        return deliveryRepository.findByProduct_ProductId(productId);
    }

    @Override
    public List<Delivery> getDeliveriesByAgentId(UUID agentId) {
        LOGGER.info("Fetching deliveries by agent ID: {}", agentId);
        return deliveryRepository.findByDeliveryAgent_AgentId(agentId);
    }

    @Override
    public Delivery createDelivery(Delivery delivery) {
        LOGGER.info("Creating a new delivery");
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery updateDelivery(UUID deliveryId, Delivery delivery) {
        LOGGER.info("Updating delivery with ID: {}", deliveryId);
        Delivery existingDelivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found with ID: " + deliveryId));

        // Update delivery details
        existingDelivery.setProduct(delivery.getProduct());
        existingDelivery.setDeliveryAgent(delivery.getDeliveryAgent());
        existingDelivery.setCustomerName(delivery.getCustomerName());
        existingDelivery.setSignature(delivery.getSignature());
        existingDelivery.setDeliveryStatus(delivery.getDeliveryStatus());
        existingDelivery.setDeliveryDate(delivery.getDeliveryDate());
        existingDelivery.setMissedDeliveryReason(delivery.getMissedDeliveryReason());
        existingDelivery.setReturnReason(delivery.getReturnReason());

        return deliveryRepository.save(existingDelivery);
    }

    @Override
    public void deleteDelivery(UUID deliveryId) {
        LOGGER.info("Deleting delivery with ID: {}", deliveryId);
        deliveryRepository.deleteById(deliveryId);
    }
}
