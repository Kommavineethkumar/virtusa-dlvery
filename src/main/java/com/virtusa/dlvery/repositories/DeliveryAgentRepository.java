package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.DeliveryAgent;
import com.virtusa.dlvery.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, UUID> {

    List<DeliveryAgent> findByAgentName(String agentName);

    List<DeliveryAgent> findByPriority(Priority priority);

    // Additional custom queries
    List<DeliveryAgent> findByAgentNameContaining(String partialAgentName);

    List<DeliveryAgent> findByAgentNameAndPriority(String agentName, Priority priority);

    List<DeliveryAgent> findByTodayDeliveryListIsNotEmpty();

    List<DeliveryAgent> findByPastPendingDeliveryIsNotEmpty();

    // Add more custom query methods as needed

}
