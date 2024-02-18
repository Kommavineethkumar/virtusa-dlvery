package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.DeliveryAgent;
import com.virtusa.dlvery.enums.Priority;
import com.virtusa.dlvery.exceptions.DeliveryAgentNotFoundException;
import com.virtusa.dlvery.repositories.DeliveryAgentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryAgentServiceImpl.class);

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Override
    public List<DeliveryAgent> getAllAgents() {
        LOGGER.info("Fetching all delivery agents");
        return deliveryAgentRepository.findAll();
    }

    @Override
    public DeliveryAgent getAgentById(UUID agentId) {
        LOGGER.info("Fetching delivery agent with ID: {}", agentId);
        return deliveryAgentRepository.findById(agentId)
                .orElseThrow(() -> new DeliveryAgentNotFoundException("Delivery agent not found with ID: " + agentId));
    }

    @Override
    public List<DeliveryAgent> getAgentsByName(String agentName) {
        LOGGER.info("Fetching delivery agents by name: {}", agentName);
        return deliveryAgentRepository.findByAgentName(agentName);
    }

    @Override
    public List<DeliveryAgent> getAgentsByPriority(Priority priority) {
        LOGGER.info("Fetching delivery agents by priority: {}", priority);
        return deliveryAgentRepository.findByPriority(priority);
    }

    @Override
    public DeliveryAgent createAgent(DeliveryAgent agent) {
        LOGGER.info("Creating a new delivery agent");
        return deliveryAgentRepository.save(agent);
    }

    @Override
    public DeliveryAgent updateAgent(UUID agentId, DeliveryAgent agent) {
        LOGGER.info("Updating delivery agent with ID: {}", agentId);
        DeliveryAgent existingAgent = deliveryAgentRepository.findById(agentId)
                .orElseThrow(() -> new DeliveryAgentNotFoundException("Delivery agent not found with ID: " + agentId));

        // Update agent details
        existingAgent.setAgentName(agent.getAgentName());
        existingAgent.setPriority(agent.getPriority());
        existingAgent.setTodayDeliveryList(agent.getTodayDeliveryList());
        existingAgent.setPastPendingDelivery(agent.getPastPendingDelivery());

        return deliveryAgentRepository.save(existingAgent);
    }

    @Override
    public void deleteAgent(UUID agentId) {
        LOGGER.info("Deleting delivery agent with ID: {}", agentId);
        deliveryAgentRepository.deleteById(agentId);
    }
}
