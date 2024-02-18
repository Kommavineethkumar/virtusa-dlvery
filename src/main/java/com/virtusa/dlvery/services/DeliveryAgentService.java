package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.DeliveryAgent;
import com.virtusa.dlvery.enums.Priority;

import java.util.List;
import java.util.UUID;

public interface DeliveryAgentService {
    List<DeliveryAgent> getAllAgents();
    DeliveryAgent getAgentById(UUID agentId);
    List<DeliveryAgent> getAgentsByName(String agentName);
    List<DeliveryAgent> getAgentsByPriority(Priority priority);
    DeliveryAgent createAgent(DeliveryAgent agent);
    DeliveryAgent updateAgent(UUID agentId, DeliveryAgent agent);
    void deleteAgent(UUID agentId);
}
