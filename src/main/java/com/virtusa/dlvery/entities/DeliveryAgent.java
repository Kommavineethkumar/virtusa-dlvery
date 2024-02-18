package com.virtusa.dlvery.entities;

import com.virtusa.dlvery.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery_agent_tbl")
public class DeliveryAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID agentId;

    @NotBlank(message = "AgentName is required")
    @Size(min = 1, max = 255, message = "AgentName must be between 1 and 255 characters")
    private String agentName;

    @NotNull(message = "Priority is required")
    @Enumerated
    private Priority priority;

    // LinkedLists for delivery lists
    @OneToMany
    private LinkedList<TodayDeliveryList> todayDeliveryList;
    @OneToMany
    private LinkedList<PastPendingDelivery> pastPendingDelivery;

    // Constructor without ID
    public DeliveryAgent(String agentName, Priority priority,
                         LinkedList<TodayDeliveryList> todayDeliveryList, LinkedList<PastPendingDelivery> pastPendingDelivery) {
        this.agentName = agentName;
        this.priority = priority;
        this.todayDeliveryList = todayDeliveryList;
        this.pastPendingDelivery = pastPendingDelivery;
    }
}
